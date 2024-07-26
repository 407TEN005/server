package sixgarlic.potenday.travelroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sixgarlic.potenday.travelroom.dto.RoomCreateRequest;
import sixgarlic.potenday.travelroom.dto.RoomDetailResponse;
import sixgarlic.potenday.travelroom.dto.RoomResponse;
import sixgarlic.potenday.travelroom.service.RoomService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/users/{userId}/travel-rooms")
    public ResponseEntity createTravelRoom(
            @PathVariable Long userId,
            @RequestBody RoomCreateRequest roomCreateRequest) {

        Long id = roomService.createTravelRoom(userId, roomCreateRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/travel-rooms")
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/users/{userId}/travel-rooms")
    public ResponseEntity getAllTravelRooms(@PathVariable Long userId) {

        List<RoomResponse> rooms = roomService.getAllTravelRooms(userId);

        return ResponseEntity.ok(rooms);

    }

    @PostMapping("/users/{userId}/travel-rooms/{roomId}")
    public ResponseEntity joinTravelRoom(
            @PathVariable Long userId,
            @PathVariable Long roomId) {

        roomService.joinTravelRoom(userId, roomId);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/travel-rooms/{roomId}")
    public ResponseEntity getRoomDetail(@PathVariable Long roomId) {

        RoomDetailResponse roomDetailResponse = roomService.getRoomDetail(roomId);

        return ResponseEntity.ok(roomDetailResponse);
    }
}
