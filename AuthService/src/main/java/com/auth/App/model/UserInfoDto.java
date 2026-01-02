package com.auth.App.model;

import com.auth.App.entities.UserInfo;
import tools.jackson.databind.PropertyNamingStrategy;
import tools.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.class)
public class UserInfoDto extends UserInfo {

    private String username;
    private String lastName;
    private String email;
    private String phoneNumber;
}
