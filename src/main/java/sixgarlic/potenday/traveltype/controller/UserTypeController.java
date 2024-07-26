package sixgarlic.potenday.traveltype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sixgarlic.potenday.traveltype.service.UserTypeService;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserTypeController {

    private final UserTypeService userTypeService;

    @GetMapping("/users/{userId}/travel-rooms/{roomId}/travel-type")
    public ResponseEntity getTravelType(
            @PathVariable Long userId,
            @PathVariable Long roomId) {

        return ResponseEntity.ok(userTypeService.getTravelType(userId, roomId));

    }
}
