package sixgarlic.potenday.commandment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sixgarlic.potenday.commandment.dto.ClovaRequestDto;
import sixgarlic.potenday.commandment.dto.CommandmentRequest;
import sixgarlic.potenday.commandment.dto.Message;
import sixgarlic.potenday.commandment.model.Commandment;
import sixgarlic.potenday.global.auth.CustomOAuth2User;
import sixgarlic.potenday.global.common.SystemPrompt;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.test.model.Test;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.travelroom.model.Travel;
import sixgarlic.potenday.travelroom.repository.RoomRepository;
import sixgarlic.potenday.travelroom.repository.TravelRepository;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommandmentService {


    private final String url = "https://clovastudio.stream.ntruss.com/testapp/v1/chat-completions/HCX-003";
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final TravelRepository travelRepository;

    @Transactional
    public void saveCommandments(Room room, List<String> contents) {

        room.removeCommandments();

        List<Commandment> commandments = contents.stream()
                .map(content -> new Commandment(content))
                .collect(Collectors.toList());

        room.addCommandments(commandments);

        roomRepository.save(room);
    }

    /**
     * 여행방 내 구성원들의 성향 및 유형으로 클로바에게 요청하는 메서드
     * @param customOAuth2User
     * @param roomId
     * @return
     */
    @Transactional
    public List<String> requestClovaX(CustomOAuth2User customOAuth2User, Long roomId) {

        User user = userRepository.findByKakaoId(customOAuth2User.getKakaoId())
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("여행방을 찾을 수 없습니다."));

        Travel travel = travelRepository.findByUserAndRoom(user, room)
                .orElseThrow(() -> new NoSuchElementException("여행 정보를 찾을 수 없습니다."));

        if (!travel.isAdmin()) {
            throw new AccessDeniedException("방을 생성할 권한이 없습니다.");
        }

        Message systemMessage = new Message("system", SystemPrompt.SYSTEM_PROMPT);
        Message userMessage = new Message("user", deriveUserPrompt(room));

        ClovaRequestDto clovaRequestDto = new ClovaRequestDto(List.of(systemMessage, userMessage));

        HttpEntity httpEntity = new HttpEntity(clovaRequestDto, httpHeaders);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                List<String> commandments = extractCommandment(responseBody);
                saveCommandments(room, commandments);
                return commandments;
            } else {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("HTTP request failed : " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON response: " + e.getMessage(), e);
        }

    }

    /**
     * 비 로그인시 테스트의 결과로 클로버에게 요청하는 메서드
     * @param commandmentRequest
     * @return
     */
    public List<String> requestClovaX(CommandmentRequest commandmentRequest) {
        Message system = new Message("system", SystemPrompt.SYSTEM_PROMPT);
        Message user = new Message("user", deriveUserPrompt(commandmentRequest));

        ClovaRequestDto clovaRequestDto = new ClovaRequestDto(List.of(system, user));

        HttpEntity httpEntity = new HttpEntity(clovaRequestDto, httpHeaders);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                List<String> commandments = extractCommandment(responseBody);
                return commandments;
            } else {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("HTTP request failed : " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON response: " + e.getMessage(), e);
        }
    }

    private List<String> extractCommandment(String responseBody) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;

        rootNode = objectMapper.readTree(responseBody);

        String content = rootNode.path("result").path("message").path("content").asText();

        return new ArrayList<>(List.of(content.split("\n")));
    }

    private String deriveUserPrompt(CommandmentRequest commandmentRequest) {
        String role = commandmentRequest.getRole().getRole();
        String myTravelType = commandmentRequest.getMyTravelType().toString();
        List<String> answers = commandmentRequest.getAnswers().stream()
                .map(answer -> answer.toString())
                .collect(Collectors.toList());
        String targetTravelType = commandmentRequest.getTargetTravelType().toString();

        String result = role + "(" + myTravelType;
        for (String answer : answers) {
            result += ", " + answer;
        }
        result += ") ";

        if (targetTravelType.contains("P")) {
            result += "부모";
        } else {
            result += "자녀";
        }

        result += "(" + targetTravelType + ")";
        return result;
    }

    private String deriveUserPrompt(Room room) {

        StringBuilder stringBuilder = new StringBuilder();
        room.getTravels().stream()
                .map(travel -> travel.getUserType())
                .forEach(userType -> {
                    FamilyRole role = userType.getRole();
                    TravelType travelType = userType.getTravelType();
                    List<Test> tests = userType.getTests();
                     stringBuilder.append(role.getRole()).append("(").append(travelType.toString());
                    for (Test test : tests) {
                        stringBuilder.append(", ").append(test.getAnswer().toString());
                    }
                    stringBuilder.append(") ");
                });
        return stringBuilder.toString();
    }

}
