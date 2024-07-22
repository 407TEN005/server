package sixgarlic.potenday.travelroom.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.member.model.Member;
import sixgarlic.potenday.traveltype.model.TravelType;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private TravelRoom travelRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TravelType travelType;

    @Enumerated(EnumType.STRING)
    private TravelRole travelRole;
}
