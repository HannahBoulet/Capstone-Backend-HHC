package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.ClientInfo;
import com.hungerhealthcoalition.backendhhc.repository.ClientInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Info")
public class ClientInfoController {
    private ClientInfoRepository clientInfoRepository;

    public ClientInfoController(ClientInfoRepository clientInfoRepository)
    {
        this.clientInfoRepository = clientInfoRepository;
    }

    @GetMapping
    public List<ClientInfo> getAllClientsInfos(){
        return clientInfoRepository.findAll();
    }

    /**
     * Methods needed:
     * get specific client
     * add client
     * delete client
     * update client
     *
     * */
















}
