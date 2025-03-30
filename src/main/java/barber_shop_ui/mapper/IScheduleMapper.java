package barber_shop_ui.mapper;

import barber_shop_ui.controller.response.ClientScheduleAppointmentResponse;
import barber_shop_ui.controller.response.SaveScheduleResponse;
import barber_shop_ui.controller.response.ScheduleAppointmentMonthResponse;
import barber_shop_ui.entity.ScheduleEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import br.com.dio.barbershopui.controller.request.SaveScheduleRequest;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMontResponse(final int year, final int month, final List<ScheduleEntity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);
}
