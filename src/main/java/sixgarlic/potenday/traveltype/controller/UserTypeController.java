package sixgarlic.potenday.traveltype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sixgarlic.potenday.test.dto.TestResultResponse;
import sixgarlic.potenday.traveltype.service.UserTypeService;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserTypeController {

    private final UserTypeService userTypeService;

    @GetMapping("/travel-rooms/{roomId}/users/{userId}/travel-type")
    public ResponseEntity getTravelType(
            @PathVariable Long roomId,
            @PathVariable Long userId) {

        TestResultResponse travelType = userTypeService.getTravelType(userId, roomId);

        return ResponseEntity.ok(travelType);

    }
}
