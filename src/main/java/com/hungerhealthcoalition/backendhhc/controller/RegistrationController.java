package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.Registration;
import com.hungerhealthcoalition.backendhhc.repository.RegistrationRepository;
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
     * @return List of all Client Logins
     */
    @GetMapping
    public List<Registration> getAllPairings() {
        return registrationRepository.findAll();
    }

    /**
     * Retrieves client by ID
     *
     * @param id the id of the client login.
     * @return specified client
     */
    @GetMapping("/{id}")
    public List<Registration> getClientbyID(@PathVariable("id") String id) {
        Optional<Registration> RegistrationOptional = registrationRepository.findRegistrationById(id);
        List<Registration> result = new ArrayList<>();

        RegistrationOptional.ifPresent(result::add);
        return result;
    }

    /**
     * Retrieves client by eventId
     *
     * @param eventId the id of the client login.
     * @return specified client
     */
    @GetMapping("/eventID/{eventId}")
    public List<Registration> getClientbyEventID(@PathVariable("eventId") String eventId) {
        Optional<Registration> RegistrationOptional = registrationRepository.findRegistrationByEventID(eventId);
        List<Registration> result = new ArrayList<>();

        RegistrationOptional.ifPresent(result::add);
        return result;
    }

    /**
     * Add's a new pairing to the Registration table
     *
     * @param registration the new registration data to be added
     * @return the new registration data
     */
    @PostMapping
    public Registration addClientLogin(@RequestBody Registration registration) {
        registrationRepository.save(registration);
        return registration;
    }

    /**
     * Updates an exisiting clients Login
     *
     * @param id          the id of the client to update
     * @param registration the object which is being updated with updated pairing information
     * @return the updated clients login information
     */
    @PutMapping("/{id}")
    public List<Registration> updateRegistration(@PathVariable("id") String id, @RequestBody Registration registration) {
        Optional<Registration> existingRegistrationOptional = registrationRepository.findById(id);
        List<Registration> result = new ArrayList<>();

        if (existingRegistrationOptional.isPresent()) {
            Registration existingRegistration = existingRegistrationOptional.get();
            existingRegistration.setid(registration.getid());
            existingRegistration.setEventID(registration.getEventID());
            registrationRepository.save(existingRegistration);
            result.add(existingRegistration);
        }

        return result;
    }

    /**
     * Deletes a pairing object by ID
//     *
     * @param id the id of the client login to delete
     * @return list containing the deleted login otherwise and empty list
     */
    @DeleteMapping("/{id}")
    public List<Registration> deleteClientLogin(@PathVariable("id") String id) {
        List<Registration> result = new ArrayList<>();
        Optional<Registration> registrationOptional = registrationRepository.findById(id);
        if (registrationOptional.isPresent()) {
            registrationRepository.deleteById(id);
            result.add(registrationOptional.get());
        }
        return result;
    }

}
