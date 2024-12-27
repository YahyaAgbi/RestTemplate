package exemple.eurekaclient.services;


import exemple.eurekaclient.entities.Client;
import exemple.eurekaclient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
      @Autowired
        private ClientRepository clientRepository;

        public List<Client> findAll() {
            return clientRepository.findAll();
        }

        public Client findById(Long id) throws Exception {
            return clientRepository.findById(id).orElseThrow(()->
                    new Exception("Invalid client ID"));
        }

        public Client addClient(Client client) {
            clientRepository.save(client);
            return client;
        }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public Client update(Long id, Client client) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient == null) {
            return null;
        }
        existingClient.setNom(client.getNom());
        existingClient.setAge(client.getAge());

        return clientRepository.save(existingClient);
    }

}
