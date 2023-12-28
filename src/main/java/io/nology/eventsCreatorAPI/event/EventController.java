package io.nology.eventsCreatorAPI.event;

import io.nology.eventsCreatorAPI.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Long id) {
        Optional<Event> found = this.eventService.getById(id);

        if(found.isPresent()) {
            return new ResponseEntity<Event>(found.get(), HttpStatus.OK);
        }

        throw new NotFoundException(String.format("Event with id: %d does not exist", id));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventCreateDTO data) {
        Event newEvent = this.eventService.createEvent(data);
        return new ResponseEntity<Event>(newEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteById(@PathVariable Long id) {
        boolean deleted = this.eventService.deleteById(id);

        if(deleted == true) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        throw new NotFoundException(String
                .format("Event with id: %d does not exist, could not delete", id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Event> updateById(@PathVariable Long id,
        @Valid @RequestBody EventUpdateDTO data) {

        Optional<Event> updated = this.eventService.updateById(id, data);

        if(updated.isPresent()) {
            return new ResponseEntity<Event>(updated.get(), HttpStatus.OK);
        }

        throw new NotFoundException(String
                .format("Event with id: %d does not exist, could not update", id));
    }

}
