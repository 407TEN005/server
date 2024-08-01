package sixgarlic.potenday.commandment.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.test.model.Answer;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.traveltype.model.TravelType;

import java.util.List;

@Data
public class CommandmentRequest {
    private FamilyRole role;
    private TravelType myTravelType;
    private List<Answer> answers;
    private TravelType targetTravelType;

    @Builder
    public CommandmentRequest(FamilyRole role, TravelType myTravelType, List<Answer> answers, TravelType targetTravelType) {
        this.role = role;
        this.myTravelType = myTravelType;
        this.answers = answers;
        this.targetTravelType = targetTravelType;
    }
}
