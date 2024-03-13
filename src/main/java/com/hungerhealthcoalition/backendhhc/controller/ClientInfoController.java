package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.ClientInfo;
import com.hungerhealthcoalition.backendhhc.repository.ClientInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Info")
public class ClientInfoController {
    private ClientInfoRepository clientInfoRepository;

    public ClientInfoController(ClientInfoRepository clientInfoRepository) {
        this.clientInfoRepository = clientInfoRepository;
    }

    /**
     * Retrieves all Client Info
     *
     * @return List of all Client Infos
     */
    @GetMapping
    public List<ClientInfo> getAllClientsInfos() {
        return clientInfoRepository.findAll();
    }

    /**
     * Retrieves client by ID
     *
     * @param id the id of the client info.
     * @return specified client
     */
    @GetMapping("/{id}")
    public List<ClientInfo> getClientbyID(@PathVariable("id") String id) {
        Optional<ClientInfo> clientInfoOptional = clientInfoRepository.findById(id);
        List<ClientInfo> result = new ArrayList<>();
        clientInfoOptional.ifPresent(result::add);
        return result;
    }

    /**
     * Add's a new client info to the Info table
     *
     * @param clientInfo the new client info data to be added
     * @return the new client info
     */
    @PostMapping
    public ClientInfo addClientInfo(@RequestBody ClientInfo clientInfo) {
        clientInfoRepository.save(clientInfo);
        return clientInfo;
    }

    /**
     * Deletes a client user object by ID
     *
     * @param id the id of the client to delete
     * @return list containing the deleted info otherwise and empty list
     */
    @DeleteMapping("/{id}")
    public List<ClientInfo> deleteClientInfo(@PathVariable("id") String id) {
        List<ClientInfo> result = new ArrayList<>();
        Optional<ClientInfo> clientOptional = clientInfoRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientInfoRepository.deleteById(id);
            result.add(clientOptional.get());
        }
        return result;
    }

    /**
     * Updates an existing clients Login
     *
     * @param id         the id of the client login to update
     * @param clientInfo the object which is being updated with updated client information
     * @return the updated clients information
     */
    @PutMapping("/{id}")
    public List<ClientInfo> updateClientInfo(@PathVariable("id") String id, @RequestBody ClientInfo clientInfo) {
        Optional<ClientInfo> existingClientOptional = clientInfoRepository.findById(id);
        List<ClientInfo> result = new ArrayList<>();

        if (existingClientOptional.isPresent()) {
            ClientInfo existingClient = existingClientOptional.get();
            existingClient.setPassword(clientInfo.getPassword());
            existingClient.setClientFirst(clientInfo.getClientFirst());
            existingClient.setClientLast(clientInfo.getClientLast());
            existingClient.setFoodBox(clientInfo.getFoodBox());
            existingClient.setMedications(clientInfo.getMedications());
            existingClient.setClientPicture(clientInfo.getClientPicture());
            existingClient.setGoalType(clientInfo.getGoalType());
            existingClient.setGoalTarget(clientInfo.getGoalTarget());
            existingClient.setCurrentGoal(clientInfo.getCurrentGoal());
        }

        return result;
    }
}
