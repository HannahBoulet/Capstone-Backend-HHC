package com.hungerhealthcoalition.backendhhc.repository;

import com.hungerhealthcoalition.backendhhc.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
public Optional<Registration> findRegistrationById(int id);
    public Optional<Registration> findRegistrationByEventID(int eventID);

    public Optional<Registration> findRegistrationByIdAndEventID(int id, int eventID);
    public void deleteRegistrationByIdAndEventID(int id, int eventID);

    public void deleteRegistrationByEventID(int eventID);
}
