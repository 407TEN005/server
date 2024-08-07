package sixgarlic.potenday.user.model;

import jakarta.persistence.*;
import lombok.*;
import sixgarlic.potenday.test.model.Test;
import sixgarlic.potenday.travelroom.model.Travel;
import sixgarlic.potenday.traveltype.model.UserType;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String kakaoId;

    private String nickname;

    private String authority;

    @Enumerated
    @Setter
    private UserStatus status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserType> userTypes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Travel> travels = new ArrayList<>();

    @Builder
    public User(Long id, String kakaoId, String nickname, String authority, UserStatus status) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.authority = authority;
        this.status = status;
    }
}
