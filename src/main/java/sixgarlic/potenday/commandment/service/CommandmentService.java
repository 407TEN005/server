package sixgarlic.potenday.commandment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sixgarlic.potenday.commandment.dto.CommandmentSaveRequest;
import sixgarlic.potenday.commandment.model.Commandment;
import sixgarlic.potenday.travelroom.model.Room;
import sixgarlic.potenday.travelroom.repository.RoomRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommandmentService {

    private final RoomRepository roomRepository;

    @Transactional
    public void saveCommandments(Long roomId, CommandmentSaveRequest commandmentSaveRequest) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException("여행방을 찾을 수 없습니다."));

        room.removeCommandments();

        List<String> contents = commandmentSaveRequest.getCommandments();

        List<Commandment> commandments = contents.stream()
                .map(content -> new Commandment(content))
                .collect(Collectors.toList());

        room.addCommandments(commandments);

        roomRepository.save(room);
    }
}
