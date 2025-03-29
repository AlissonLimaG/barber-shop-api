package barber_shop_ui.controller;


import barber_shop_ui.controller.response.SaveScheduleResponse;
import barber_shop_ui.mapper.IScheduleMapper;
import barber_shop_ui.service.impl.ScheduleService;
import barber_shop_ui.service.query.impl.ScheduleQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import br.com.dio.barbershopui.controller.request.SaveScheduleRequest;

@RestController
@RequestMapping("schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;
    private final ScheduleQueryService queryService;
    private final IScheduleMapper mapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SaveScheduleResponse save (@RequestBody @Valid SaveScheduleRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable final long id){
        service.delete(id);
    }


}
