package sixgarlic.potenday.test.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Enumerated(EnumType.STRING)
    private FamilyRole familyRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Test(Long id, Answer answer, FamilyRole familyRole, User user) {
        this.id = id;
        this.answer = answer;
        this.familyRole = familyRole;
        this.user = user;
    }
}
