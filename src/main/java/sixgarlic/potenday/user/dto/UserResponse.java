package sixgarlic.potenday.user.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.model.UserStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class UserResponse {

    private Long id;
    private String kakaoId;
    private String nickname;
    private String authority;
    private UserStatus status;
    private TravelType travelType;
    private String familyRole;

    @Builder
    private UserResponse(Long id, String kakaoId, String nickname, String authority, UserStatus status, TravelType travelType, String familyRole) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.authority = authority;
        this.status = status;
        this.travelType = travelType;
        this.familyRole = familyRole;
    }

    public static UserResponse from(User user) {

        TravelType travelType = user.getUserTypes().stream()
                .filter(userType -> userType.isDefault())
                .findFirst()
                .map(userType -> userType.getTravelType())
                .orElse(null);

        String role = user.getUserTypes().stream()
                .filter(userType -> userType.isDefault())
                .findFirst()
                .map(userType -> userType.getRole().getRole())
                .orElse(null);

        return UserResponse.builder()
                .id(user.getId())
                .kakaoId(user.getKakaoId())
                .nickname(user.getNickname())
                .authority(user.getAuthority())
                .status(user.getStatus())
                .travelType(travelType)
                .familyRole(role)
                .build();
    }
}
