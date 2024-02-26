package com.hungerhealthcoalition.backendhhc.repository;

import com.hungerhealthcoalition.backendhhc.model.ClientLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ClientLoginRepository extends JpaRepository<ClientLogin, String> {
    @Query("SELECT q FROM ClientLogin q WHERE q.userName LIKE %?1%")
    List<ClientLogin> getClientLoginBy(String client);
}
