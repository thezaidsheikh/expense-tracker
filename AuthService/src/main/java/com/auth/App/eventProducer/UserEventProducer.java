package com.auth.App.eventProducer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserEventProducer {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
}
