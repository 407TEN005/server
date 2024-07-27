package sixgarlic.potenday.user.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.model.UserStatus;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class UserResponse {

    private Long id;
    private String kakaoId;
    private String nickname;
    private String authority;
    private UserStatus status;
    private List<TravelType> travelTypes;

    @Builder
    private UserResponse(Long id, String kakaoId, String nickname, String authority, UserStatus status, List<TravelType> travelTypes) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.authority = authority;
        this.status = status;
        this.travelTypes = travelTypes;
    }

    public static UserResponse from(User user) {

        List<TravelType> travelTypes = user.getUserTypes().stream()
                .map(userType -> userType.getTravelType())
                .collect(Collectors.toList());

        return UserResponse.builder()
                .id(user.getId())
                .kakaoId(user.getKakaoId())
                .nickname(user.getNickname())
                .authority(user.getAuthority())
                .status(user.getStatus())
                .travelTypes(travelTypes)
                .build();
    }
}
