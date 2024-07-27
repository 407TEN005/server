package sixgarlic.potenday.travelroom.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.commandment.model.Commandment;
import sixgarlic.potenday.global.common.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private String roomName;

    private LocalDate startDate;

    private LocalDate endDate;

    private int headcount;

    private int maxHeadcount;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Travel> travels = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commandment> commandments = new ArrayList<>();

    @Builder
    private Room(Long id, String roomName, LocalDate startDate, LocalDate endDate, int maxHeadcount) {
        this.id = id;
        this.roomName = roomName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxHeadcount = maxHeadcount;
        this.headcount = 1;
    }

    public void addHeadcount() {
        this.headcount += 1;
    }

    public void addTravel(Travel travel) {
        travels.add(travel);
        travel.setRoom(this);
    }

    public void addCommandments(List<Commandment> commandments) {
        for (Commandment commandment : commandments) {
            this.commandments.add(commandment);
            commandment.setRoom(this);
        }
    }

    public void removeCommandments() {
        if (!this.commandments.isEmpty()) {
            this.commandments.clear();
        }
    }
}
