package sixgarlic.potenday.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sixgarlic.potenday.test.dto.TestRequestDto;
import sixgarlic.potenday.test.service.TestService;
import sixgarlic.potenday.traveltype.dto.TravelTypeDto;
import sixgarlic.potenday.traveltype.service.TravelTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/tests")
public class TestController {

    private final TestService testService;
    private final TravelTypeService travelTypeService;

    @PostMapping
    public ResponseEntity submitTestResults(
            @PathVariable Long userId,
            @RequestBody TestRequestDto testRequestDto) {

        testService.saveTestResults(userId, testRequestDto);
        TravelTypeDto travelTypeDto = travelTypeService.deriveTravelType(userId, testRequestDto);

        return ResponseEntity.ok().body(travelTypeDto);
    }
}
