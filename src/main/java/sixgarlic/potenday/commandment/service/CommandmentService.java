package sixgarlic.potenday.commandment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sixgarlic.potenday.commandment.dto.ClovaRequestDto;
import sixgarlic.potenday.commandment.dto.CommandmentRequest;
import sixgarlic.potenday.commandment.dto.CommandmentSaveRequest;
import sixgarlic.potenday.commandment.dto.Message;
import sixgarlic.potenday.commandment.model.Commandment;
import sixgarlic.potenday.global.common.SystemPrompt;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.test.model.Test;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.travelroom.repository.RoomRepository;
import sixgarlic.potenday.traveltype.model.TravelType;

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

    @Transactional
    public void saveCommandments(Long roomId, CommandmentSaveRequest commandmentSaveRequest) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException("여행방을 찾을 수 없습니다."));

        room.removeCommandments();

        List<String> contents = commandmentSaveRequest.getCommandments();

        List<Commandment> commandments = contents.stream()
                .map(content -> new Commandment(content))
                .collect(Collectors.toList());

        room.addCommandments(commandments);

        roomRepository.save(room);
    }

//    public void reqeustClovaX(Long roomId) {
//        HttpHeaders httpHeaders = SetHttpHeaders();
//
//    }

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

        result += "(" + targetTravelType + ") \n이 데이터로 10개의 계명을 도출해줘.";
        return result;
    }

    public String deriveUserPrompt(Long roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException("여행방을 찾을 수 없습니다."));

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
        String result = stringBuilder.toString();
        return result;
    }

}
