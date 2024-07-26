package sixgarlic.potenday.traveltype.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum TravelType {

    P1("열정 넘치는 불꽃 부모", null, null, null),
    P2("긍정 에너지 만렙 부모", null, null, null),
    P3("걱정 많은 안전제일 부모", null, null, null),
    P4("느긋한 여유파 부모", null, null, null),
    P5("융통성 있는 중재자 부모", null, null, null),
    P6("꼼꼼한 현실주의 부모", null, null, null),
    C1("열정 가득 도전 자녀", null, null, null),
    C2("긍정 뿜뿜 모험 자녀", null, null, null),
    C3("걱정 많은 신중 자녀", null, null, null),
    C4("느긋한 안전 자녀", null, null, null),
    C5("균형 잡힌 협력 자녀", null, null, null),
    C6("꼼꼼한 실속 자녀", null, null, null);

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
