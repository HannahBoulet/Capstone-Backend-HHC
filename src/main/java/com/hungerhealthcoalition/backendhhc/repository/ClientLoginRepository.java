package com.hungerhealthcoalition.backendhhc.repository;

import com.hungerhealthcoalition.backendhhc.model.ClientLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLoginRepository extends JpaRepository<ClientLogin, String> {

}
