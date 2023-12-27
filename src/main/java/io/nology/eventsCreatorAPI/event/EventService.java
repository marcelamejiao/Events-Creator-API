package io.nology.eventsCreatorAPI.event;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Event> getAll() {
        return this.eventRepository.findAll();
    }

    public Event createEvent(EventCreateDTO data) {
        Event newEvent = modelMapper.map(data, Event.class);
        Event created = this.eventRepository.save(newEvent);

        return created;
    }
}
