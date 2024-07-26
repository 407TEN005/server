package sixgarlic.potenday.traveltype.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum TravelType {

    P1("열정 넘치는 불꽃 부모", "활기찬 여행 분위기를 조성하며 새로운 경험에 언제나 활짝 열려있군요!", "다소 강한 감정 표현이 앞서 갈등이 발생하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#도전은내운명", "#감정폭발주의", "#즉흥여행의달인"))),
    P2("긍정 에너지 만렙 부모", "스트레스 상황에도 유연하게 대처하는군요!", "지나친 낙관으로 현실적 문제를 간과하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#웃음백신접종완료", "#모험은나의힘", "#자녀의견존중매니아"))),
    P3("걱정 많은 안전제일 부모", "철저한 준비와 안전을 고려하는군요!", "과도한 걱정으로 가족의 즐거움이 저하되지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#걱정이곧사랑", "#계획은필수", "#부모님말씀은진리"))),
    P4("느긋한 여유파 부모", "스트레스 없는 여행 분위기를 조성하는군요!", "지나친 여유로 관리에 실패하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#평화주의자", "#안전지대선호", "#여유있는계획"))),
    P5("융통성 있는 중재자 부모", "가족 간 의견을 잘 조율하는군요!", "결정 과정에서 우유부단해지지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#중재의달인", "#적당한도전환영", "#융통성있는계획"))),
    P6("꼼꼼한 현실주의 부모", "체계적인 여행 계획을 수립하는군요!", "지나친 계산으로 즉흥적인 즐거움이 감소하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#안전한도전선호", "#즉흥속의계획", "#부모의경험중시"))),
    C1("열정 가득 도전 자녀", "모험심과 호기심이 풍부하군요!", "위험한 상황을 초래하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#폭풍감정표현", "#도전이곧인생", "#즉흥여행전문가"))),
    C2("긍정 뿜뿜 모험 자녀", "어떤 상황에서도 즐거움을 찾는군요!", "현실적인 문제 인식이 부족하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#웃음폭포", "#모험은나의것", "#즉흥여행선호"))),
    C3("걱정 많은 신중 자녀", "안전과 세부사항을 주의 깊게 관찰하는군요!", "새로운 경험에 대해 지나치게 두려워하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#걱정대마왕", "#안전지대사수", "#계획은필수"))),
    C4("느긋한 안전 자녀", "스트레스 없이 여행을 즐기는군요!", "여행 준비나 결정에 소극적이지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#평화주의자", "#여유있는계획선호", "#부모님의견존중"))),
    C5("균형 잡힌 협력 자녀", "가족 간 의견을 잘 조율하는군요!", "본인의 선호도 표현이 부족하지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#중재전문가", "#적당한도전환영", "#내의견도중요"))),
    C6("꼼꼼한 실속 자녀", "효율적인 여행 계획을 수립하는군요!", "지나친 실용성 추구로 특별한 경험을 놓치지 않도록 주의하세요!", new ArrayList<>(Arrays.asList("#현실주의자", "#안전한도전선호", "#계획은필수")));

    private final String description;
    private final String advantage;
    private final String caution;
    private final List<String> hashtag;

    TravelType(String description, String advantage, String caution, List<String> hashtag) {
        this.description = description;
        this.advantage = advantage;
        this.caution = caution;
        this.hashtag = hashtag;
    }
}
