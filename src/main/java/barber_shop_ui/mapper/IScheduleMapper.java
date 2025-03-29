package barber_shop_ui.mapper;

import barber_shop_ui.controller.response.SaveScheduleResponse;
import barber_shop_ui.entity.ScheduleEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import br.com.dio.barbershopui.controller.request.SaveScheduleRequest;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);
}
