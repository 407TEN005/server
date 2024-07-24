package sixgarlic.potenday.traveltype.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.traveltype.model.TravelType;

@Data
public class TravelTypeDto {

    private String id;
    private String name;
    private String description;
    private String imageUrl;

    @Builder
    public TravelTypeDto(String id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public static TravelTypeDto from(TravelType travelType) {
        return TravelTypeDto.builder()
                .id(travelType.getId())
                .name(travelType.getName())
                .description(travelType.getDescription())
                .imageUrl(travelType.getImageUrl())
                .build();
    }
}
