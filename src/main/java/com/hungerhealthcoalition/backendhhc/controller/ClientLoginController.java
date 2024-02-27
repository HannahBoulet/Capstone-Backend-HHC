package com.hungerhealthcoalition.backendhhc.controller;
import ch.qos.logback.core.net.server.Client;
import com.hungerhealthcoalition.backendhhc.model.ClientLogin;
import com.hungerhealthcoalition.backendhhc.repository.ClientLoginRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientLoginController {
    @Autowired
    private ClientLoginRepository clientrepo;

    @GetMapping("/User")
    public List<ClientLogin> getUser(@RequestParam("search") Optional<String> searchParam)
    {
        return searchParam.map(param->clientrepo.getClientLoginBy(param)).orElse(clientrepo.findAll());
    }

    @GetMapping("/User/{UserName}")
    public ResponseEntity<String> readUsername(@PathVariable("UserName") String id)
    {
        return ResponseEntity.of(clientrepo.findById(id).map(ClientLogin::getClientID));
    }

    @PostMapping("/User")
    public ClientLogin addClient(@RequestParam String User)
    {
        //Client,userName,password
        ClientLogin c = new ClientLogin();
        c.setClientID(User);
        c.setClientPassword(User);
        c.setUserName(User);
        return clientrepo.save(c);
    }
}
