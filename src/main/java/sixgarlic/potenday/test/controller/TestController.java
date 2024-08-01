package sixgarlic.potenday.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sixgarlic.potenday.test.dto.TestResultResponse;
import sixgarlic.potenday.test.dto.TestDeriveRequest;
import sixgarlic.potenday.test.service.TestService;
import sixgarlic.potenday.traveltype.model.TravelType;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("/users/{userId}/tests")
    public ResponseEntity submitTestResults(
            @PathVariable Long userId,
            @RequestBody TestDeriveRequest testDeriveRequest) {

        TestResultResponse testResultResponse = testService.submitTestResults(userId, testDeriveRequest);

        return ResponseEntity.ok(testResultResponse);
    }

    @PostMapping("/test-without-auth")
    public ResponseEntity submitTestResults(@RequestBody TestDeriveRequest testDeriveRequest) {

        TravelType travelType = testService.submitTestResults(testDeriveRequest);

        return ResponseEntity.ok(TestResultResponse.from(travelType));
    }

}
