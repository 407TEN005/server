package sixgarlic.potenday.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sixgarlic.potenday.test.dto.TestDeriveRequest;
import sixgarlic.potenday.test.dto.TestResultResponse;
import sixgarlic.potenday.test.model.Answer;
import sixgarlic.potenday.test.model.Test;
import sixgarlic.potenday.test.repository.TestRepository;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.traveltype.model.UserType;
import sixgarlic.potenday.traveltype.service.UserTypeService;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserTypeService userTypeService;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public TestResultResponse submitTestResults(String kakaoId, TestDeriveRequest testDeriveRequest) {

        User user = userRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new NoSuchElementException("회원 정보를 찾을 수 없습니다."));

        UserType userType = userTypeService.deriveTravelType(user, testDeriveRequest);

        for (Answer answer : testDeriveRequest.getAnswers()) {
            Test test = Test.builder()
                    .answer(answer)
                    .userType(userType)
                    .build();

            testRepository.save(test);
        }

        return TestResultResponse.from(userType.getTravelType());
    }

    public TestResultResponse submitTestResults(TestDeriveRequest testDeriveRequest) {

        TravelType travelType = userTypeService.deriveTravelType(testDeriveRequest);

        return TestResultResponse.from(travelType);
    }
}
