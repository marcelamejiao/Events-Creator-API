package io.nology.eventsCreatorAPI.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.text.ParseException;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EventCreateDTO {

    @NotBlank
    private String eventName;

    @NotBlank
    private OffsetDateTime startDate;

    @NotBlank
    private OffsetDateTime endDate;

    @NotBlank
    private String location;

    @NotBlank
    private String label;

    public EventCreateDTO(String eventName,
                 OffsetDateTime startDate,
                 OffsetDateTime endDate,
                 String  location,
                 String label)  throws ParseException {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.label = label;
    }

}