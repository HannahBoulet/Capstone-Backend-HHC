package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.repository.ClientInfoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Info")
public class ClientInfoController {
    private ClientInfoRepository clientInfoRepository;

    public ClientInfoController(ClientInfoRepository clientInfoRepository)
    {
        this.clientInfoRepository = clientInfoRepository;
    }




}
