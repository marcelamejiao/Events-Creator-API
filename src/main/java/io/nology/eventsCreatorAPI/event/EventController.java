package io.nology.eventsCreatorAPI.event;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        List<Event> allEvents = this.eventService.getAll();
        return new ResponseEntity<List<Event>>(allEvents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventCreateDTO data) {
        Event newEvent = this.eventService.createEvent(data);
        return new ResponseEntity<Event>(newEvent, HttpStatus.CREATED);
    }

}
