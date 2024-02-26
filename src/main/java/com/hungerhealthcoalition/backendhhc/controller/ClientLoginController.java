package com.hungerhealthcoalition.backendhhc.controller;
import com.hungerhealthcoalition.backendhhc.model.ClientLogin;
import com.hungerhealthcoalition.backendhhc.repository.ClientLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientLoginController {
    @Autowired
    private ClientLoginRepository quoteRepo;


    @GetMapping("/User")
    public List<ClientLogin> getUser(@RequestParam("search") Optional<String> searchParam)
    {

    }




}
