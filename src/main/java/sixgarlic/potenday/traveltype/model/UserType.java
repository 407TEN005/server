package sixgarlic.potenday.traveltype.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.test.model.Test;
import sixgarlic.potenday.travelroom.model.Travel;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.test.model.FamilyRole;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserType {

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

    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Travel> travels = new ArrayList<>();

    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> tests = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TravelType travelType;

    @Builder
    private UserType(Long id, boolean isDefault, FamilyRole role, User user, TravelType travelType) {
        this.id = id;
        this.isDefault = isDefault;
        this.role = role;
        this.user = user;
        this.travelType = travelType;
    }
}
