package barber_shop_ui.service.query.impl;
import barber_shop_ui.entity.ClientEntity;
import barber_shop_ui.exception.NotFoundException;
import barber_shop_ui.exception.PhoneInUseException;
import barber_shop_ui.repository.ClientRepository;
import barber_shop_ui.service.query.IClientQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final ClientRepository clientRepository;

    @Override
    public ClientEntity findById(long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o cliente de id: " + id));
    }

    @Override
    public List<ClientEntity> list() {
        return clientRepository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if(clientRepository.existsByPhone(phone)){
            var message = "O telefone " + phone + "já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(long id, String phone) {
        var optional = clientRepository.findByPhone(phone);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)){
            var message = "O telefone " + phone + "já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if(clientRepository.existsByEmail(email)){
            var message = "O e-mail " + email + "já está em uso";
            throw new PhoneInUseException(message);
        }

    }

    @Override
    public void verifyEmail(long id, String email) {
        var optional = clientRepository.findByPhone(email);
        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), email)){
            var message = "O e-mail " + email + "já está em uso";
            throw new PhoneInUseException(message);
        }
    }
}
