package sixgarlic.potenday.travelroom.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.traveltype.model.TravelType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoomResponse {

    private Long id;
    private String roomName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int headcount;
    private int maxHeadcount;
    private boolean existCommandments;
//    private List<TravelType> travelTypes;

    @Builder
    private RoomResponse(Long id, String roomName, LocalDate startDate, LocalDate endDate, int headcount, int maxHeadcount, boolean existCommandments) {
        this.id = id;
        this.roomName = roomName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.headcount = headcount;
        this.maxHeadcount = maxHeadcount;
        this.existCommandments = existCommandments;
//        this.travelTypes = travelTypes;
    }

    public static RoomResponse from(Room room) {

//        List<TravelType> travelTypes = room.getTravels().stream()
//                .map(travel -> travel.getUserType())
//                .map(userType -> userType.getTravelType())
//                .collect(Collectors.toList());

        return RoomResponse.builder()
                .id(room.getId())
                .roomName(room.getRoomName())
                .startDate(room.getStartDate())
                .endDate(room.getEndDate())
                .headcount(room.getHeadcount())
                .maxHeadcount(room.getMaxHeadcount())
                .existCommandments(!room.getCommandments().isEmpty())
//                .travelTypes(travelTypes)
                .build();
    }
}
