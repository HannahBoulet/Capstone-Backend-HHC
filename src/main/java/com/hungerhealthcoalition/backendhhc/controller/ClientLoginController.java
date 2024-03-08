package com.hungerhealthcoalition.backendhhc.controller;

import com.hungerhealthcoalition.backendhhc.model.ClientLogin;
import com.hungerhealthcoalition.backendhhc.repository.ClientLoginRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Logins")
public class ClientLoginController {

    private ClientLoginRepository clientLoginRepository;

    public ClientLoginController(ClientLoginRepository clientLoginRepository) {
        this.clientLoginRepository = clientLoginRepository;
    }

    /**
     * Retrieves all Client Logins
     *
     * @return List of all Client Logins
     */
    @GetMapping
    public List<ClientLogin> getAllClients() {
        return clientLoginRepository.findAll();
    }

    /**
     * Retrieves client by ID
     *
     * @param id the id of the client login.
     * @return specified client
     */
    @GetMapping("/{id}")
    public List<ClientLogin> getClientbyID(@PathVariable("id") String id) {
        Optional<ClientLogin> clientLoginOptional = clientLoginRepository.findById(id);
        List<ClientLogin> result = new ArrayList<>();

        clientLoginOptional.ifPresent(result::add);
        return result;
    }

    /**
     * Add's a new client login to the Login table
     *
     * @param clientLogin the new clientlogin data to be added
     * @return the new cleint login
     */
    @PostMapping
    public ClientLogin addClientLogin(@RequestBody ClientLogin clientLogin) {
        clientLoginRepository.save(clientLogin);
        return clientLogin;
    }

    /**
     * Updates an exisiting clients Login
     *
     * @param id          the id of the client login to update
     * @param clientLogin the object which is being updated with updated client information
     * @return the updated clients login information
     */
    @PutMapping("/{id}")
    public List<ClientLogin> updateClientLogin(@PathVariable("id") String id, @RequestBody ClientLogin clientLogin) {
        Optional<ClientLogin> existingClientOptional = clientLoginRepository.findById(id);
        List<ClientLogin> result = new ArrayList<>();

        if (existingClientOptional.isPresent()) {
            ClientLogin existingClient = existingClientOptional.get();
            existingClient.setUserName(clientLogin.getUserName());
            existingClient.setPassword(clientLogin.getPassword());
            clientLoginRepository.save(existingClient);
            result.add(existingClient);
        }

        return result;
    }

    /**
     * Deletes a client login object by ID
     *
     * @param id the id of the client login to delete
     * @return list containing the deleted login otherwise and empty list
     */
    @DeleteMapping("/{id}")
    public List<ClientLogin> deleteClientLogin(@PathVariable("id") String id) {
        List<ClientLogin> result = new ArrayList<>();
        Optional<ClientLogin> clientOptional = clientLoginRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientLoginRepository.deleteById(id);
            result.add(clientOptional.get());
        }
        return result;
    }

}
