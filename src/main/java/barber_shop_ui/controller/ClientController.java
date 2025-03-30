package barber_shop_ui.controller;

import barber_shop_ui.controller.request.UpdateClientRequest;
import barber_shop_ui.controller.response.ClientDetailResponse;
import barber_shop_ui.controller.response.ListClientResponse;
import barber_shop_ui.controller.response.SaveClientResponse;
import barber_shop_ui.controller.response.UpdateClientResponse;
import barber_shop_ui.mapper.IClientMapper;
import barber_shop_ui.service.impl.ClientService;
import barber_shop_ui.service.query.impl.ClientQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.dio.barbershopui.controller.request.SaveClientRequest;

import java.util.List;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientQueryService clientQueryService;
    private final IClientMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);
        clientService.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id,request);
        clientService.update(entity);
        return mapper.toUpdateResponse(entity);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable final long id){
        clientService.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = clientQueryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping()
    List<ListClientResponse> list(){
        var entites = clientQueryService.list();
        return mapper.toListResponse(entites);
    }
}
