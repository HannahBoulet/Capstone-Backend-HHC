package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.Registration;
import com.hungerhealthcoalition.backendhhc.repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/Registration")
public class RegistrationController {


    private RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * Retrieves all parings
     *
     * @return List of all parings
     */
    @GetMapping
    public List<Registration> getAllPairings() {
        return registrationRepository.findAll();
    }

    /**
     * Retrieves a list of parings of the given ID
     *
     * @param id the id of the event.
     * @return specified client
     */
    @GetMapping("/{id}")
    public List<Registration> getPairingByID(@PathVariable("id") int id) {
        List<Registration> result = registrationRepository.findRegistrationByClientInfoClientID(id);
        return result;
    }

    /**
     * Retrieves list of client's by eventID
     *
     * @param eventID the id of the event.
     * @return specified client
     */
    @GetMapping("/eventID/{eventID}")
    public List<Registration> getParingByEventID(@PathVariable("eventID") int eventID) {
        return registrationRepository.findRegistrationByEventsEventId(eventID);
    }

    /**
     * Adds a new pairing to the Registration table
     *
     * @param registration the new registration data to be added
     * @return the new registration data
     */
    @PostMapping
    public Registration addPairing(@RequestBody Registration registration) {


        // Check for duplicate registration based on clientInfo and eventID
        Optional<Registration> existingRegistration = registrationRepository.findRegistrationByClientInfoClientIDAndEventsEventId(
                registration.getClientInfo().getClientID(), registration.getEvents().getEventId());

        if (existingRegistration.isPresent()) {
            // Throw an exception for clarity
            throw new RuntimeException("Registration with client ID " + registration.getClientInfo().getClientID() +
                    " and eventID " + registration.getEvents().getEventId() + " already exists");
        }

        // Persist the new registration
        return registrationRepository.save(registration);
    }

    /**
     * Updates an existing Pairing
     *
     * @param clientId          the id of the paring to update
     * @param eventID          the eventID of the paring to update
     * @param registration the object which is being updated with updated pairing information
     * @return the updated clients login information
     */
    @PutMapping("/{id}/{eventID}")
    public List<Registration> updateRegistration(@PathVariable("id") int clientId, @PathVariable("eventID") int eventID, @RequestBody Registration registration) {
        // Validate client existence first
        if (!registrationRepository.existsByClientInfoClientID(clientId)) {
            throw new RuntimeException("Client with ID " + clientId + " not found");
        }

        Optional<Registration> existingRegistrationOptional = registrationRepository.findRegistrationByClientInfoClientIDAndEventsEventId(clientId, eventID);
        List<Registration> result = new ArrayList<>();

        if (existingRegistrationOptional.isPresent()) {
            Registration existingRegistration = existingRegistrationOptional.get();

            existingRegistration.setEvents(registration.getEvents());
            registrationRepository.save(existingRegistration);
            result.add(existingRegistration);
        }

        return result;
    }

    /**
     * Deletes a pairing object by ID
     *
     * @param id the id of the paring to delete
     * @param eventID the eventID of the paring to delete
     * @return list containing the deleted paring otherwise and empty list
     */
    @DeleteMapping("/{id}/{eventID}")
    @Transactional
    public ResponseEntity<Void> deletePairingByIdAndEventID(@PathVariable("id") int id, @PathVariable("eventID") int eventID) {
        registrationRepository.deleteRegistrationByClientInfoClientIDAndEventsEventId(id, eventID);

        return ResponseEntity.noContent().build();
    }



    /**
     * Deletes all pairing with the eventID
     * @param eventID the eventID of the paring to delete
     * @return list containing the deleted paring otherwise and empty list
     */
    @DeleteMapping("/{eventID}")
    @Transactional
    public ResponseEntity<Void> deletePairingByEventID(@PathVariable("eventID") int eventID) {
            registrationRepository.deleteRegistrationByEventsEventId(eventID);

        return ResponseEntity.noContent().build();
    }


}
