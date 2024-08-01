package sixgarlic.potenday.test.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.traveltype.model.UserType;
import sixgarlic.potenday.user.model.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @Builder
    public Test(Long id, Answer answer, UserType userType) {
        this.id = id;
        this.answer = answer;
        this.userType = userType;
    }
}
