package sixgarlic.potenday.travelroom.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sixgarlic.potenday.travelroom.dto.RoomCreateRequest;
import sixgarlic.potenday.travelroom.dto.RoomDetailResponse;
import sixgarlic.potenday.travelroom.dto.RoomResponse;
import sixgarlic.potenday.travelroom.model.Travel;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.travelroom.repository.TravelRepository;
import sixgarlic.potenday.travelroom.repository.RoomRepository;
import sixgarlic.potenday.traveltype.model.UserType;
import sixgarlic.potenday.traveltype.repository.UserTypeRepository;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;
    private final TravelRepository travelRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createTravelRoom(Long userId, RoomCreateRequest roomCreateRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));

        UserType userType = userTypeRepository.findByUserIdAndIsDefault(userId, true)
                .orElseThrow(() -> new NoSuchElementException("여행 유형을 찾을 수 없습니다."));

        Room room = roomRepository.save(roomCreateRequest.toTravelRoom());

        Travel travel = Travel.builder()
                .room(room)
                .user(user)
                .userType(userType)
                .familyRole(userType.getRole())
                .isAdmin(true)
                .build();

        room.addTravel(travel);
        travelRepository.save(travel);
        return room.getId();

    }

    @Transactional
    public void joinTravelRoom(Long userId, Long roomId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));

        UserType userType = userTypeRepository.findByUserIdAndIsDefault(userId, true)
                .orElseThrow(() -> new NoSuchElementException("여행 유형을 찾을 수 없습니다."));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("여행방을 찾을 수 없습니다."));

        room.addHeadcount();

        Travel travel = Travel.builder()
                .room(room)
                .user(user)
                .userType(userType)
                .familyRole(userType.getRole())
                .isAdmin(false)
                .build();

        room.addTravel(travel);
        travelRepository.save(travel);
        roomRepository.save(room);
    }

    public List<RoomResponse> getAllTravelRooms(Long userId, String sort) {

        Sort sortDirection = Sort.by("createdAt");
        if ("desc".equalsIgnoreCase(sort)) {
            sortDirection = sortDirection.descending();
        } else {
            sortDirection = sortDirection.ascending();
        }

        List<Room> rooms = roomRepository.findAllByUserId(userId, sortDirection);

        return rooms.stream()
                .map(room -> {
                    Travel adminTravel = travelRepository.findAdminTravelByRoomId(room.getId())
                            .orElseThrow(() -> new NoSuchElementException("방장의 정보를 찾을 수 없습니다."));
                    return RoomResponse.from(room, adminTravel.getUserType().getTravelType());
                })
                .collect(Collectors.toList());

    }

    public RoomDetailResponse getRoomDetail(Long roomId) {
        Room room = roomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new NoSuchElementException("해당 여방행 정보가 없습니다."));
        return RoomDetailResponse.from(room);
    }
}
