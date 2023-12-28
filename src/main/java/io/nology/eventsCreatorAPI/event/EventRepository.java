package io.nology.eventsCreatorAPI.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByLabel(String label);

    List<Event> findByLocation(String location);
}
