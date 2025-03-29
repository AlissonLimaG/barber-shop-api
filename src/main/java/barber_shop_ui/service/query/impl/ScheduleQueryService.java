package barber_shop_ui.service.query.impl;

import barber_shop_ui.entity.ScheduleEntity;
import barber_shop_ui.exception.NotFoundException;
import barber_shop_ui.exception.ScheduleInUseException;
import barber_shop_ui.repository.ScheduleRepository;
import barber_shop_ui.service.query.IScheduleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleEntity findById(long id) {
        return scheduleRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Agendamento não encontrado")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return scheduleRepository.findByStartAtGreaterThanEqualAndEndAtLessThenEqualOrderByStartAtEndAt(startAt,endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if(scheduleRepository.existsByStartAtAndEndAt(startAt,endAt)){
            var message = "Já existe um cliente agendado para este horário";
            throw new ScheduleInUseException(message);
        }
    }
}
