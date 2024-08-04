package sixgarlic.potenday.commandment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sixgarlic.potenday.commandment.dto.CommandmentRequest;
import sixgarlic.potenday.commandment.dto.CommandmentSaveRequest;
import sixgarlic.potenday.commandment.service.CommandmentService;
import sixgarlic.potenday.global.auth.CustomOAuth2User;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommandmentController {

    private final CommandmentService commandmentService;

    @GetMapping("/travel-rooms/{roomId}/commandments")
    public ResponseEntity requestClovaX(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @PathVariable Long roomId) {
        List<String> commandments = commandmentService.requestClovaX(customOAuth2User, roomId);
        return ResponseEntity.ok(commandments);
    }

    @PostMapping("/derive-commandments-without-auth")
    public ResponseEntity requestClovaX(@RequestBody CommandmentRequest commandmentRequest) {
        List<String> commandments = commandmentService.requestClovaX(commandmentRequest);
        return ResponseEntity.ok(commandments);
    }
}
