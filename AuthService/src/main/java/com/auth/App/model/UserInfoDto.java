package com.auth.App.model;

import com.auth.App.entities.UserInfo;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo {
    private String username;
    private String lastName;
    private String email;
    private String phoneNumber;
}
