package barber_shop_ui.repository;

import barber_shop_ui.entity.ClientEntity;
import barber_shop_ui.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long> {

    List<ScheduleEntity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtEndAt(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );

    boolean existsStartAtAndEndAt(
            final OffsetDateTime startAt,
            final OffsetDateTime endAt
    );
}
