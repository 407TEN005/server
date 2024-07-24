package sixgarlic.potenday.test.dto;

import lombok.Data;
import sixgarlic.potenday.test.model.Answer;
import sixgarlic.potenday.test.model.FamilyRole;

import java.util.List;

@Data
public class TestRequestDto {

    private FamilyRole familyRole;
    private List<Answer> answers;

}
