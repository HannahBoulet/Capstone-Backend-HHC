package com.hungerhealthcoalition.backendhhc.controller;

import com.hungerhealthcoalition.backendhhc.model.Events;
import com.hungerhealthcoalition.backendhhc.repository.EventsRepository;
import jdk.jfr.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Events")
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

    /**
     * Add a new event to the Events table
     *
     * @param events the new events to be added
     * @return new events
     */
    @PostMapping
    public Events addEvents(@RequestBody Events events){
        eventsRepository.save(events);
        return events;
    }

    /**
     * Updates an existing event
     *
     * @param id  the id of the events to update
     * @param events  the object being updated
     * @return the updated events
     */
    @PutMapping("/{id}")
    public List<Events> updateEvents(@PathVariable("id") String id, @RequestBody Events events) {
        Optional<Events> existingEventsOptional = eventsRepository.findById(id);
        List<Events> result = new ArrayList<>();

        if(existingEventsOptional.isPresent()){
            Events existingEvents = existingEventsOptional.get();
            existingEvents.setEventDate(events.getEventDate());
            existingEvents.setEventLimit(events.getEventLimit());
            existingEvents.setEventDescription(events.getEventDescription());
            existingEvents.setEventName(events.getEventName());
            existingEvents.setEventPicture(events.getEventPicture());
            eventsRepository.save(existingEvents);
            result.add(existingEvents);
        }

        return result;
    }

    /**
     * Deletes an events object by ID
     *
     * @param id the id of event to delete
     * @return list containing the deleted events otherwise empty list
     */
    @DeleteMapping("/{id}")
    public List<Events> deleteEvents(@PathVariable("id") String id){
        List<Events> result = new ArrayList<>();
        Optional<Events> eventsOptional = eventsRepository.findById(id);
        if (eventsOptional.isPresent()) {
            eventsRepository.deleteById(id);
            result.add(eventsOptional.get());
        }
        return result;
    }

}
