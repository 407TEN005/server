package sixgarlic.potenday.travelroom.dto;

import lombok.Builder;
import lombok.Data;
import sixgarlic.potenday.travelroom.model.Room;

import java.time.LocalDate;

@Data
public class RoomCreateRequest {

    private String roomName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxHeadcount;

    @Builder
    private RoomCreateRequest(String roomName, LocalDate startDate, LocalDate endDate, int maxHeadcount) {
        this.roomName = roomName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxHeadcount = maxHeadcount;
    }

    public Room toTravelRoom() {
        return Room.builder()
                .roomName(roomName)
                .startDate(startDate)
                .endDate(endDate)
                .maxHeadcount(maxHeadcount)
                .build();
    }
}
