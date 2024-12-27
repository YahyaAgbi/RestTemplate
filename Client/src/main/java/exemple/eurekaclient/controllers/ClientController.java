package exemple.eurekaclient.controllers;


import exemple.eurekaclient.entities.Client;
import exemple.eurekaclient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/client/{id}")
    public Client findById(@PathVariable Long id) {
        try {
            return clientService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/client")
    public Client save(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @DeleteMapping("/client/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("/client/{id}")
    public Client update(@PathVariable Long id,@RequestBody Client client) {
        return clientService.update(id, client);
    }
}