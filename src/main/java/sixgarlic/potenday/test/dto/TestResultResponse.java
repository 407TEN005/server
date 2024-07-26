package sixgarlic.potenday.test.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.traveltype.model.TravelType;

import java.util.List;

@Data
public class TestResultResponse {

    private TravelType travelType;
    private String description;
    private String advantage;
    private String caution;
    private List<String> hashtag;

    @Builder
    private TestResultResponse(TravelType travelType, String description, String advantage, String caution, List<String> hashtag) {
        this.travelType = travelType;
        this.description = description;
        this.advantage = advantage;
        this.caution = caution;
        this.hashtag = hashtag;
    }

    public static TestResultResponse from(TravelType travelType) {
        return TestResultResponse.builder()
                .travelType(travelType)
                .description(travelType.getDescription())
                .advantage(travelType.getAdvantage())
                .caution(travelType.getCaution())
                .hashtag(travelType.getHashtag())
                .build();
    }
}
