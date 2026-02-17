package com.expense_tracker.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserInfoDto {

    private String userId;

    private String firstName;

    private String lastName;

    private Long phoneNumber;

    private String email;

    private String profilePic;

    public UserInfo transformToUserInfo(){
        return UserInfo.builder().firstName(firstName).lastName(lastName).userId(userId).profilePic(profilePic).email(email).phoneNumber(phoneNumber).build();
    }
}
