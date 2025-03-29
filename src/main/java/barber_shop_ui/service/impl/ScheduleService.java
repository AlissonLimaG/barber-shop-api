package barber_shop_ui.service.impl;

import barber_shop_ui.entity.ScheduleEntity;
import barber_shop_ui.repository.ScheduleRepository;
import barber_shop_ui.service.IScheduleService;
import barber_shop_ui.service.query.impl.ScheduleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {

    private final ScheduleQueryService queryService;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return scheduleRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        scheduleRepository.deleteById(id);
    }
}
