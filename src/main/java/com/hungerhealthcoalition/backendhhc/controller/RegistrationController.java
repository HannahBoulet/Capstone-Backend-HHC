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
    public List<Registration> getPairingByID(@PathVariable("id") String id) {
        Optional<Registration> RegistrationOptional = registrationRepository.findRegistrationById(id);
        List<Registration> result = new ArrayList<>();

        RegistrationOptional.ifPresent(result::add);
        return result;
    }

    /**
     * Retrieves list of client's by eventID
     *
     * @param eventID the id of the event.
     * @return specified client
     */
    @GetMapping("/eventID/{eventID}")
    public List<Registration> getParingByEventID(@PathVariable("eventID") String eventID) {
        Optional<Registration> RegistrationOptional = registrationRepository.findRegistrationByEventID(eventID);
        List<Registration> result = new ArrayList<>();

        RegistrationOptional.ifPresent(result::add);
        return result;
    }

    /**
     * Adds a new pairing to the Registration table
     *
     * @param registration the new registration data to be added
     * @return the new registration data
     */
    @PostMapping
    public Registration addPairing(@RequestBody Registration registration) {
        Optional<Registration>  existingRegistration = registrationRepository.findRegistrationByIdAndEventID(registration.getId(), registration.getEventID());
        if (existingRegistration.isPresent()) {
            //throw new DuplicateRegistrationException("Registration with eventID and id already exists");
            Registration duplicateRegistration = new Registration();
            duplicateRegistration.setRegistrationID(-1);
            duplicateRegistration.setEventID("-1");
            duplicateRegistration.setId("-1");
            return duplicateRegistration;
        }
        return registrationRepository.save(registration);
    }

    /**
     * Updates an existing Pairing
     *
     * @param id          the id of the paring to update
     * @param eventID          the eventID of the paring to update
     * @param registration the object which is being updated with updated pairing information
     * @return the updated clients login information
     */
    @PutMapping("/{id}/{eventID}")
    public List<Registration> updateRegistration(@PathVariable("id") String id, @PathVariable("eventID") String eventID,  @RequestBody Registration registration) {
        Optional<Registration> existingRegistrationOptional = registrationRepository.findRegistrationByIdAndEventID(id,eventID);
        List<Registration> result = new ArrayList<>();

        if (existingRegistrationOptional.isPresent()) {
            Registration existingRegistration = existingRegistrationOptional.get();
            existingRegistration.setId(registration.getId());
            existingRegistration.setEventID(registration.getEventID());
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
    public ResponseEntity<Void> deletePairingByIdAndEventID(@PathVariable("id") String id, @PathVariable("eventID") String eventID) {
        registrationRepository.deleteRegistrationByIdAndEventID(id, eventID);

        return ResponseEntity.noContent().build();
    }
//    @DeleteMapping("/{id}/{eventID}")
//    public List<Registration> deletePairingByIdAndEventID(@PathVariable("id") String id, @PathVariable("eventID") String eventID) {
//        List<Registration> result = new ArrayList<>();
//        Optional<Registration> registrationOptional = registrationRepository.findRegistrationByIdAndEventID(id,eventID);
//        if (registrationOptional.isPresent()) {
//            registrationRepository.deleteRegistrationByIdAndEventID(id, eventID);
//            result.add(registrationOptional.get());
//        }
//        return result;
//    }


    /**
     * Deletes all pairing with the eventID
     * @param eventID the eventID of the paring to delete
     * @return list containing the deleted paring otherwise and empty list
     */
    @DeleteMapping("/{eventID}")
    @Transactional
    public ResponseEntity<Void> deletePairingByEventID(@PathVariable("eventID") String eventID) {
            registrationRepository.deleteRegistrationByEventID(eventID);

        return ResponseEntity.noContent().build();
    }
//    @DeleteMapping("/{eventID}")
//    public List<Registration> deletePairingByEventID(@PathVariable("eventID") String eventID) {
//        List<Registration> result = new ArrayList<>();
//        Optional<Registration> registrationOptional = registrationRepository.findRegistrationByEventID(eventID);
//        if (registrationOptional.isPresent()) {
//            registrationRepository.deleteRegistrationByEventID(eventID);
//            result.add(registrationOptional.get());
//        }
//        return result;
//    }

}
