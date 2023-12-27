package io.nology.eventsCreatorAPI.event;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Getter
@Setter
public class EventCreateDTO {

    @NotBlank
    private String eventName;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    @NotBlank
    private String location;

    @NotBlank
    private String label;

    public EventCreateDTO(String eventName,
                 String startDate,
                 String endDate,
                 String location,
                 String label)  throws ParseException {
        this.eventName = eventName;
        this.startDate = OffsetDateTime.parse(startDate);
        this.endDate = OffsetDateTime.parse(endDate);
        this.location = location;
        this.label = label;
    }

}
