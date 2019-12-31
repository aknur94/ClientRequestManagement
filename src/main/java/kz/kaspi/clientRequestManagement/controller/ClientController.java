package kz.kaspi.clientRequestManagement.controller;

import kz.kaspi.clientRequestManagement.exception.ClientException;
import kz.kaspi.clientRequestManagement.model.Client;
import kz.kaspi.clientRequestManagement.repo.CustomClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    @Qualifier("customClientRepo")
    CustomClientRepo clientRepo;

    @GetMapping
    public Iterable<Client> getAllClients() {
        return clientRepo.findAll();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientRepo.findById(id).get();
    }

    @PostMapping
    public ResponseEntity newClient(@RequestBody Client client) {
        try {
            return ResponseEntity.ok(clientRepo.clientSave(client));
        } catch (ClientException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editClient(@RequestBody Client client, @PathVariable Long id) {
        Client editClient = clientRepo.findById(id).orElse(client);
        editClient.setPhone(client.getPhone());
        editClient.setFio(client.getFio());
        editClient.setOrganization(client.getOrganization());
        editClient.setBin(client.getBin());
        try {
            return ResponseEntity.ok(clientRepo.clientSave(editClient));
        } catch (ClientException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepo.deleteById(id);
    }

}
