package sixgarlic.potenday.travelroom.model;

import jakarta.persistence.*;
import lombok.*;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.traveltype.model.UserType;
import sixgarlic.potenday.user.model.User;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @Setter
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private FamilyRole familyRole;

    private boolean isAdmin;

    @Builder
    private Travel(Long id, User user, Room room, UserType userType, FamilyRole familyRole, boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.userType = userType;
        this.familyRole = familyRole;
        this.isAdmin = isAdmin;
    }
}
