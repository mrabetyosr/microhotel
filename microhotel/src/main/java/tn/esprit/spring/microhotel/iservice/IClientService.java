package tn.esprit.spring.microhotel.iservice;

import tn.esprit.spring.microhotel.entity.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client saveClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);
}
