package sixgarlic.potenday.commandment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sixgarlic.potenday.commandment.dto.CommandmentSaveRequest;
import sixgarlic.potenday.commandment.service.CommandmentService;

@RestController
@RequiredArgsConstructor
public class CommandmentController {

    private final CommandmentService commandmentService;

    @PostMapping("/travel-rooms/{roomId}/commandments")
    public ResponseEntity saveCommandments(
            @PathVariable Long roomId,
            @RequestBody CommandmentSaveRequest commandmentSaveRequest) {

        commandmentService.saveCommandments(roomId, commandmentSaveRequest);

        return ResponseEntity.ok().build();
    }
}
