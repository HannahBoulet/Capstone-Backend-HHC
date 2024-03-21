package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.Registration;
import com.hungerhealthcoalition.backendhhc.repository.RegistrationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/Logins")
public class RegistrationController {






    private RegistrationRepository RegistrationRepository;

    public RegistrationController(RegistrationRepository RegistrationRepository) {
        this.RegistrationRepository = RegistrationRepository;
    }

    /**
     * Retrieves all Client Logins
     *
     * @return List of all Client Logins
     */
    @GetMapping
    public List<Registration> getAllPairings() {
        return RegistrationRepository.findAll();
    }

    /**
     * Retrieves client by ID
     *
     * @param id the id of the client login.
     * @return specified client
     */
    @GetMapping("/{id}")
    public List<Registration> getClientbyID(@PathVariable("id") String id) {
        Optional<Registration> RegistrationOptional = RegistrationRepository.findById(id);
        List<Registration> result = new ArrayList<>();

        RegistrationOptional.ifPresent(result::add);
        return result;
    }


//    @GetMapping("/{id}")
//    public List<Registration> getClientbyEventID(@PathVariable("eventID") String eventID) {
//        Optional<Registration> RegistrationOptional = RegistrationRepository.findById(eventID);
//        List<Registration> result = new ArrayList<>();
//
//        RegistrationOptional.ifPresent(result::add);
//        return result;
//    }

    /**
     * Add's a new client login to the Login table
     *
     * @param registration the new registration data to be added
     * @return the new registration data
     */
    @PostMapping
    public Registration addClientLogin(@RequestBody Registration registration) {
        RegistrationRepository.save(registration);
        return registration;
    }

    /**
     * Updates an exisiting clients Login
     *
     * @param id          the id of the client to update
     * @param registration the object which is being updated with updated client information
     * @return the updated clients login information
     */
    @PutMapping("/{id}")
    public List<Registration> updateRegistration(@PathVariable("id") String id, @RequestBody Registration registration) {
        Optional<Registration> existingRegistrationOptional = RegistrationRepository.findById(id);
        List<Registration> result = new ArrayList<>();

        if (existingRegistrationOptional.isPresent()) {
            Registration existingRegistration = existingRegistrationOptional.get();
            existingRegistration.setid(registration.getid());
            existingRegistration.setEventID(registration.getEventID());
            RegistrationRepository.save(existingRegistration);
            result.add(existingRegistration);
        }

        return result;
    }

    /**
     * Deletes a client login object by ID
//     *
     * @param id the id of the client login to delete
     * @return list containing the deleted login otherwise and empty list
     */
    @DeleteMapping("/{id}")
    public List<Registration> deleteClientLogin(@PathVariable("id") String id) {
        List<Registration> result = new ArrayList<>();
        Optional<Registration> registrationOptional = RegistrationRepository.findById(id);
        if (registrationOptional.isPresent()) {
            RegistrationRepository.deleteById(id);
            result.add(registrationOptional.get());
        }
        return result;
    }

}
