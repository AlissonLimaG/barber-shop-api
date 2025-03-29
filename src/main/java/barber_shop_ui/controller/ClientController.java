package barber_shop_ui.controller;

import barber_shop_ui.controller.response.SaveClientResponse;
import barber_shop_ui.service.impl.ClientService;
import barber_shop_ui.service.query.impl.ClientQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.dio.barbershopui.controller.request.SaveClientRequest;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientQueryService clientQueryService;

    @PostMapping
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){

    }

}
