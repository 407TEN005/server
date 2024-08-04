package sixgarlic.potenday.traveltype.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sixgarlic.potenday.test.dto.TestDeriveRequest;
import sixgarlic.potenday.test.dto.TestResultResponse;
import sixgarlic.potenday.test.model.Answer;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.travelroom.model.Travel;
import sixgarlic.potenday.travelroom.repository.RoomRepository;
import sixgarlic.potenday.travelroom.repository.TravelRepository;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.traveltype.model.UserType;
import sixgarlic.potenday.traveltype.repository.UserTypeRepository;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.model.UserStatus;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;
    private final UserRepository userRepository;
    private final TravelRepository travelRepository;
    private final RoomRepository roomRepository;

    public TestResultResponse getTravelType(Long userId, Long roomId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("회원 정보를 찾을 수 없습니다."));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));
        Travel travel = travelRepository.findByUserAndRoom(user, room)
                .orElseThrow(() -> new NoSuchElementException("유형을 찾을 수 없습니다."));
        return TestResultResponse.from(travel.getUserType().getTravelType());
    }

    @Transactional
    public UserType deriveTravelType(User user, TestDeriveRequest testDeriveRequest) {

        List<Answer> answers = testDeriveRequest.getAnswers();
        FamilyRole familyRole = testDeriveRequest.getFamilyRole();

        double baseScore = calculateBaseScore(answers);
        double adjustedScore = applyWeightsAndAdjustments(baseScore, answers);

        TravelType travelType = TravelType.valueOf(checkParentOrChild(familyRole) + classifyTravelType(adjustedScore)) ;

        UserType userType = UserType.builder()
                .travelType(travelType)
                .isDefault(user.getStatus() == UserStatus.NEW)
                .role(familyRole)
                .user(user)
                .build();

        userTypeRepository.save(userType);

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        return userType;

    }

    public TravelType deriveTravelType(TestDeriveRequest testDeriveRequest) {

        List<Answer> answers = testDeriveRequest.getAnswers();
        FamilyRole familyRole = testDeriveRequest.getFamilyRole();

        double baseScore = calculateBaseScore(answers);
        double adjustedScore = applyWeightsAndAdjustments(baseScore, answers);

        return TravelType.valueOf(checkParentOrChild(familyRole) + classifyTravelType(adjustedScore));

    }

    private double calculateBaseScore(List<Answer> answers) {
        double score = 0.0;
        for (Answer answer : answers) {
            System.out.println("answer = " + answer);
            System.out.println("score = " + score);
            switch (answer) {
                case PL1:
                    score += 1 * 1.5;
                    break;
                case PL2:
                    score += 2 * 1.5;
                    break;
                case PL3:
                    score += 3 * 1.5;
                    break;
                case PL4:
                    score += 4 * 1.5;
                    break;
                case NE1:
                    score += 1 * 1.3;
                    break;
                case NE2:
                    score += 2 * 1.3;
                    break;
                case NE3:
                    score += 3 * 1.3;
                    break;
                case NE4:
                    score += 4 * 1.3;
                    break;
                case TR1:
                    score += 4 * 1.4;
                    break;
                case TR2:
                    score += 3 * 1.4;
                    break;
                case TR3:
                    score += 2 * 1.4;
                    break;
                case TR4:
                    score += 1 * 1.4;
                    break;
                case FI1:
                    score += 1 * 1.2;
                    break;
                case FI2:
                    score += 2 * 1.2;
                    break;
                case FI3:
                    score += 3 * 1.2;
                    break;
                case FI4:
                    score += 4 * 1.2;
                    break;
                    
            }
        }
        System.out.println("가중치 적용 후 점수 = " + score);
        return score;
    }

    private double applyWeightsAndAdjustments(double baseScore, List<Answer> answers) {
        double adjustedScore = baseScore;

        for (Answer answer : answers) {
            switch (answer) {
                case PL1, NE4, TR1, FI4:
                    adjustedScore += 0.5;
                    break;
                case PL4, NE1, TR4, FI1:
                    adjustedScore -= 0.5;
                    break;
            }
        }

        System.out.println("극단적 답변 가중치 후 점수 = " + adjustedScore);

        // Combination adjustments
        if (answers.contains(Answer.PL1) && answers.contains(Answer.TR1)) {
            adjustedScore += 1.0;
        } else if (answers.contains(Answer.PL4) && answers.contains(Answer.TR4)) {
            adjustedScore -= 1.0;
        }

        if (answers.contains(Answer.NE4) && answers.contains(Answer.FI4)) {
            adjustedScore += 0.5;
        } else if (answers.contains(Answer.NE1) && answers.contains(Answer.FI1)) {
            adjustedScore -= 0.5;
        }

        if (answers.contains(Answer.PL1) && answers.contains(Answer.NE4)) {
            adjustedScore += 0.5;
        } else if (answers.contains(Answer.PL4) && answers.contains(Answer.NE1)) {
            adjustedScore -= 0.5;
        }

        System.out.println("조합 보너스 적용 후 점수 = " + adjustedScore);

        // Consistency checks
        if (answers.contains(Answer.PL1) && answers.contains(Answer.TR1)) {
            adjustedScore -= 1.0;
        } else if (answers.contains(Answer.PL4) && answers.contains(Answer.TR4)) {
            adjustedScore -= 1.0;
        }

        if (answers.contains(Answer.NE1) && answers.contains(Answer.TR1)) {
            adjustedScore -= 0.5;
        } else if (answers.contains(Answer.NE4) && answers.contains(Answer.TR4)) {
            adjustedScore -= 0.5;
        }

        System.out.println("일관성 검사 후 점수 = " + adjustedScore);

        // Re-evaluate borderline cases
        if (adjustedScore >= 18.9 && adjustedScore <= 19.0) {
            if (containsAnswerOfType(answers, "TR", 4)) {
                adjustedScore = 19.0;
            } else if (containsAnswerOfType(answers, "NE", 1)) {
                adjustedScore = 18.9;
            }
        }

        return adjustedScore;
    }

    private boolean containsAnswerOfType(List<Answer> answers, String prefix, int point) {
        return answers.stream().anyMatch(answer -> answer.name().startsWith(prefix) && answer.ordinal() % 4 == point - 1);
    }

    private String classifyTravelType(double score) {
        System.out.println("최종 점수 = " + score);
        if (score >= 19.0) {
            return "1";
        } else if (score >= 16.5) {
            return "2";
        } else if (score >= 14.0) {
            return "5";
        } else if (score >= 11.5) {
            return "6";
        } else if (score >= 8.5) {
            return "4";
        } else {
            return "3";
        }
    }

    private String checkParentOrChild(FamilyRole familyRole) {
        if (familyRole == FamilyRole.DAD || familyRole == FamilyRole.MOM) {
            return "P";
        } else {
            return "C";
        }
    }
}