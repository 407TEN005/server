package sixgarlic.potenday.traveltype.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TravelType {

    @Id
    @Column(name = "type_id")
    private String id;

    private String name;

    private String description;

    private String imageUrl;

    @Builder
    public TravelType(String id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
