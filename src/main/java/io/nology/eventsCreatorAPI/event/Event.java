package io.nology.eventsCreatorAPI.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String eventName;

    @Column
    private OffsetDateTime startDate;

    @Column
    private OffsetDateTime endDate;

    @Column
    private String  location;

    @Column
    private String label;

    public Event() {}

    public Event(String eventName,
                 OffsetDateTime startDate,
                 OffsetDateTime endDate,
                 String  location,
                 String label) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.label = label;
    }
}
