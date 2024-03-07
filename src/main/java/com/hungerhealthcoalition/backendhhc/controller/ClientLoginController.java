package com.hungerhealthcoalition.backendhhc.controller;

import com.hungerhealthcoalition.backendhhc.model.ClientLogin;
import com.hungerhealthcoalition.backendhhc.repository.ClientLoginRepository;

import com.hungerhealthcoalition.backendhhc.service.ClientLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Logins")
public class ClientLoginController {

    private ClientLoginRepository clientLoginRepository;


    public ClientLoginController(ClientLoginRepository clientLoginRepository) {
        this.clientLoginRepository = clientLoginRepository;
    }
    
    @GetMapping
    public List<ClientLogin> getAllClients()
    {
        return clientLoginRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveClient(@RequestBody ClientLogin clientLogin){
        clientLoginRepository.save(clientLogin);
        return "Saved...";
    }
   /* @GetMapping("/User/{UserName}")
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
        c.setPassword(User);
        c.setUserName(User);
        return clientrepo.save(c);
    }*/
}
