package sixgarlic.potenday.commandment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.commandment.model.Commandment;

import java.util.List;

@Data
public class CommandmentSaveRequest {

    private List<String> commandments;
//
//    @Builder
//    private CommandmentSaveRequest(List<String> commandments) {
//        this.commandments = commandments;
//    }
//
//    public Commandment toCommandment(CommandmentSaveRequest commandmentSaveRequest) {
//        return Commandment.builder()
//                .content(commandmentSaveRequest.commandments)
//                .build();
//    }
}
