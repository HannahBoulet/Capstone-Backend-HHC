package com.hungerhealthcoalition.backendhhc.controller;

import com.hungerhealthcoalition.backendhhc.model.Events;
import com.hungerhealthcoalition.backendhhc.repository.EventsRepository;
import jdk.jfr.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Logins")
public class EventsController {
    private EventsRepository eventsRepository;

    public EventsController(EventsRepository eventsRepository){
        this.eventsRepository = eventsRepository;
    }

    /**
     * Retrieves all Events
     *
     * @return list of all events
     */
    @GetMapping
    public List<Events> getAllEvents() {return eventsRepository.findAll(); }

    /**
     * Retrieves Events by ID
     *
     * @param id the id of the Event
     * @return specified Event
     */
    @GetMapping("/{id}")
    public List<Events> getEventsbyID(@PathVariable("id") String id) {
        Optional<Events> eventsOptional = eventsRepository.findById(id);
        List<Events> result = new ArrayList<>();
        eventsOptional.ifPresent(result::add);
        return result;
    }







}
