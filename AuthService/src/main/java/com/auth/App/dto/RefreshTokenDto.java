package com.auth.App.dto;

import lombok.Getter;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class RefreshTokenDto {
    private String token;
}
