package sixgarlic.potenday.travelroom.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.traveltype.model.TravelType;

import java.time.LocalDate;

@Data
@Slf4j
public class RoomResponse {

    private Long id;
    private String roomName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int headcount;
    private int maxHeadcount;
    private boolean existCommandments;
    private TravelType travelType;

    @Builder
    private RoomResponse(Long id, String roomName, LocalDate startDate, LocalDate endDate, int headcount, int maxHeadcount, boolean existCommandments, TravelType travelType) {
        this.id = id;
        this.roomName = roomName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.headcount = headcount;
        this.maxHeadcount = maxHeadcount;
        this.existCommandments = existCommandments;
        this.travelType = travelType;
    }

    public static RoomResponse from(Room room, TravelType travelType) {
        return RoomResponse.builder()
                .id(room.getId())
                .roomName(room.getRoomName())
                .startDate(room.getStartDate())
                .endDate(room.getEndDate())
                .headcount(room.getHeadcount())
                .maxHeadcount(room.getMaxHeadcount())
                .existCommandments(!room.getCommandments().isEmpty())
                .travelType(travelType)
                .build();
    }
}
