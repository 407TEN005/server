package sixgarlic.potenday.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sixgarlic.potenday.global.auth.CustomOAuth2User;
import sixgarlic.potenday.test.dto.TestResultResponse;
import sixgarlic.potenday.test.dto.TestDeriveRequest;
import sixgarlic.potenday.test.service.TestService;
import sixgarlic.potenday.traveltype.model.TravelType;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("/tests")
    public ResponseEntity submitTestResults(
            @AuthenticationPrincipal CustomOAuth2User oAuth2User,
            @RequestBody TestDeriveRequest testDeriveRequest) {

        String kakaoId = oAuth2User.getKakaoId();

        TestResultResponse testResultResponse = testService.submitTestResults(kakaoId, testDeriveRequest);

        return ResponseEntity.ok(testResultResponse);
    }

    @PostMapping("/test-without-auth")
    public ResponseEntity submitTestResults(@RequestBody TestDeriveRequest testDeriveRequest) {

        TestResultResponse testResultResponse = testService.submitTestResults(testDeriveRequest);

        return ResponseEntity.ok(testResultResponse);
    }

}
