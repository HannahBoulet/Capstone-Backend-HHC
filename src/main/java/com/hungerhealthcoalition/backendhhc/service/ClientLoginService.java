package com.hungerhealthcoalition.backendhhc.service;

import com.hungerhealthcoalition.backendhhc.model.ClientLogin;
import com.hungerhealthcoalition.backendhhc.repository.ClientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientLoginService {
    List<ClientLogin> getAllUsers();

}
