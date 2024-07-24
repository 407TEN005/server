package sixgarlic.potenday.traveltype.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.test.model.FamilyRole;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserTravelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private Long id;

    private boolean isDefault;

    @Enumerated(EnumType.STRING)
    private FamilyRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TravelType type;

    @Builder
    private UserTravelType(Long id, boolean isDefault, FamilyRole role, User user, TravelType type) {
        this.id = id;
        this.isDefault = isDefault;
        this.role = role;
        this.user = user;
        this.type = type;
    }
}
