package barber_shop_ui.service;

import barber_shop_ui.entity.ClientEntity;

public interface IClientService {

    ClientEntity save(final ClientEntity client);

    ClientEntity update(final ClientEntity client);

    void delete(final long id);

}
