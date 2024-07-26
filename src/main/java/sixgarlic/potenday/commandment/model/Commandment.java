package sixgarlic.potenday.commandment.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.travelroom.model.Room;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Commandment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commandment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
