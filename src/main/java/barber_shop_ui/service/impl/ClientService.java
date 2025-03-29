package barber_shop_ui.service.impl;

import barber_shop_ui.entity.ClientEntity;
import barber_shop_ui.repository.ClientRepository;
import barber_shop_ui.service.IClientService;
import barber_shop_ui.service.query.impl.ClientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService{

    private final ClientQueryService clientQueryService;
    private final ClientRepository clientRepository;

    @Override
    public ClientEntity save(ClientEntity client) {
        clientQueryService.verifyEmail(client.getEmail());
        clientQueryService.verifyPhone(client.getPhone());

        return clientRepository.save(client);
    }

    @Override
    public ClientEntity update(ClientEntity client) {
        clientQueryService.verifyEmail(client.getId(), client.getEmail());
        clientQueryService.verifyPhone(client.getId(), client.getPhone());

        var stored = clientQueryService.findById(client.getId());
        stored.setEmail(client.getEmail());
        stored.setName(client.getName());
        stored.setPhone(client.getPhone());

        return clientRepository.save(stored);
    }

    @Override
    public void delete(long id) {
        clientQueryService.findById(id);
        clientRepository.deleteById(id);
    }
}
