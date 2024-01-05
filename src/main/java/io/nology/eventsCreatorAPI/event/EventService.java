package io.nology.eventsCreatorAPI.event;

import io.nology.eventsCreatorAPI.exceptions.UnprocessableEntityException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;


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

    public Optional<Event> getById(Long id) {
        Optional<Event> foundEvent= eventRepository.findById(id);
        return foundEvent;
    }

    public boolean deleteById(Long id) {
        Optional<Event> foundEvent = this.eventRepository.findById(id);

        if(foundEvent.isPresent()) {
            this.eventRepository.delete(foundEvent.get());
            return true;
        }

        return false;
    }

    public Optional<Event> updateById(Long id, EventUpdateDTO data) {
        Optional<Event> foundEvent = this.getById(id);

        if (! foundEvent.isPresent()) {
            return foundEvent;
        }

        Event toUpdate = foundEvent.get();

        // If the event ended in the past, throw an error
        if (toUpdate.getEndDate().compareTo(OffsetDateTime.now()) < 0) {
            throw new UnprocessableEntityException(String
                    .format("Event with id: %d can not be updated, because the end date is in the past", id));
        }

        modelMapper.map(data, toUpdate);

        Event updatedEvent = this.eventRepository.save(toUpdate);

        return Optional.of(updatedEvent);
    }

    public List<Event> getAllByLabel(String label) {
        return this.eventRepository.findByLabel(label);
    }

    public List<Event> getAllByLocation(String location) {
        return this.eventRepository.findByLocation(location);
    }

    public List<Event> getByDate(String startDate, String endDate) {
        return this.eventRepository.findByStartDateBetween(OffsetDateTime.parse(startDate), OffsetDateTime.parse(endDate));
    }
}
