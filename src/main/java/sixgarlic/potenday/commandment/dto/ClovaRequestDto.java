package sixgarlic.potenday.commandment.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClovaRequestDto {
    private List<Message> messages;
    private final double topP = 0.8;
    private final int topK = 0;
    private final int maxTokens = 500;
    private final double temperature = 0.5;
    private final double repeatPenalty = 5.0;
    private final List<Object> stopBefore = new ArrayList<>();
    private final boolean includeAiFilters = true;
    private final int seed = 0;

    public ClovaRequestDto(List<Message> messages) {
        this.messages = messages;
    }
}
