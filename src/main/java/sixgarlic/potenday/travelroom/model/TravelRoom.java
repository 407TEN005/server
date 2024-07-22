package sixgarlic.potenday.travelroom.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sixgarlic.potenday.commandment.model.Commandment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TravelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private String roomName;

    private LocalDate startDate;

    private LocalDate endDate;

    //TODO: 나중에 기획쪽에서 확정이 되면 디데이나, 퍼센트로 여행 상태 표시 예정
//    private TravelStatus status;

    private int headcount;

    private String thumbnail;

    @OneToMany(mappedBy = "travelRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commandment> commandments = new ArrayList<>();
}
