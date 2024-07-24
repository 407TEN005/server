package sixgarlic.potenday.test.model;

import lombok.Getter;

@Getter
public enum Answer {
    PL1("완전 계획형"), PL2("조금 계획형"), PL3("조금 즉흥형"), PL4("완전 즉흥형"),
    NE1("강한 부정적 감정 표현"), NE2("실망감 표현"), NE3("제한적 타협 시도"), NE4("긍정적 태도"),
    TR1("매우 도전적"), TR2("약간 도전적"), TR3("약간 소극적"), TR4("매우 소극적"),
    FI1("부모 의견 가장 우선"), FI2("부모 의견 조금 우선"), FI3("자녀 의견 조금 우선"), FI4("자녀 의견 가장 우선");

    private String description;

    Answer(String description) {
        this.description = description;
    }

}
