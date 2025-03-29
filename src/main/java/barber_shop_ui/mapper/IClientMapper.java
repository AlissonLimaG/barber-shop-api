package barber_shop_ui.mapper;

import barber_shop_ui.entity.ClientEntity;
import org.mapstruct.Mapper;
import br.com.dio.barbershopui.controller.request.SaveClientRequest;

@Mapper(componentModel = "spring")
public interface IClientMapper {

    ClientEntity toEntity(final SaveClientRequest request);

}
