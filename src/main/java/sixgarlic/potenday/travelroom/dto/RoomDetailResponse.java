package sixgarlic.potenday.travelroom.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.traveltype.model.UserType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoomDetailResponse {

    private Long id;
    private String roomName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int headcount;
    private int maxHeadcount;
    private List<String> commandments;
    private List<UserInfo> users;

    @Builder
    private RoomDetailResponse(Long id, String roomName, LocalDate startDate, LocalDate endDate, int headcount, int maxHeadcount, List<String> commandments, List<UserInfo> users) {
        this.id = id;
        this.roomName = roomName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.headcount = headcount;
        this.maxHeadcount = maxHeadcount;
        this.commandments = commandments;
        this.users = users;
    }

    public static RoomDetailResponse from(Room room) {

        List<UserInfo> users = room.getTravels().stream()
                .map(travel -> {
                    UserType userType = travel.getUserType();
                    boolean isAdmin = travel.isAdmin();
                    return UserInfo.from(userType, isAdmin);
                })
                .collect(Collectors.toList());


        List<String> commandments = room.getCommandments().stream()
                .map(commandment -> commandment.getContent())
                .collect(Collectors.toList());

        return RoomDetailResponse.builder()
                .id(room.getId())
                .roomName(room.getRoomName())
                .startDate(room.getStartDate())
                .endDate(room.getEndDate())
                .headcount(room.getHeadcount())
                .maxHeadcount(room.getMaxHeadcount())
                .commandments(commandments)
                .users(users)
                .build();
    }

    @Data
    public static class UserInfo {

        private Long id;
        private TravelType travelType;
        private FamilyRole familyRole;
        private boolean isAdmin;

        @Builder
        private UserInfo(Long id, TravelType travelType, FamilyRole familyRole, boolean isAdmin) {
            this.id = id;
            this.travelType = travelType;
            this.familyRole = familyRole;
            this.isAdmin = isAdmin;
        }

        public static UserInfo from(UserType userType, boolean isAdmin) {
            return UserInfo.builder()
                    .travelType(userType.getTravelType())
                    .familyRole(userType.getRole())
                    .id(userType.getUser().getId())
                    .isAdmin(isAdmin)
                    .build();
        }
    }
}
