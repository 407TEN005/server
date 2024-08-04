package sixgarlic.potenday.travelroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sixgarlic.potenday.global.auth.CustomOAuth2User;
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

    @PostMapping("/travel-rooms")
    public ResponseEntity createTravelRoom(
            @AuthenticationPrincipal CustomOAuth2User oAuth2User,
            @RequestBody RoomCreateRequest roomCreateRequest) {

        String kakaoId = oAuth2User.getKakaoId();

        Long id = roomService.createTravelRoom(kakaoId, roomCreateRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/travel-rooms")
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/travel-rooms")
    public ResponseEntity getAllTravelRooms(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestParam(defaultValue = "desc") String sort) {

        String kakaoId = customOAuth2User.getKakaoId();

        List<RoomResponse> rooms = roomService.getAllTravelRooms(kakaoId, sort);

        return ResponseEntity.ok(rooms);

    }

    @PostMapping("/travel-rooms/{roomId}")
    public ResponseEntity joinTravelRoom(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable Long roomId) {

        roomService.joinTravelRoom(customOAuth2User, roomId);

        return ResponseEntity.ok().build();

    }

    @GetMapping("/travel-rooms/{roomId}")
    public ResponseEntity getRoomDetail(@PathVariable Long roomId) {

        RoomDetailResponse roomDetailResponse = roomService.getRoomDetail(roomId);

        return ResponseEntity.ok(roomDetailResponse);
    }
}
