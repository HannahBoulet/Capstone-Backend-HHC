package com.hungerhealthcoalition.backendhhc.repository;

import com.hungerhealthcoalition.backendhhc.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
public Optional<Registration> findRegistrationById(String id);
    public Optional<Registration> findRegistrationByEventID(String eventID);

    public Optional<Registration> findRegistrationByIdAndEventID(String id, String eventID);
    public void deleteRegistrationByIdAndEventID(String id, String eventID);

    public void deleteRegistrationByEventID(String eventID);
}
