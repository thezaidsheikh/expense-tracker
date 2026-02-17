package com.expense_tracker.userservice.service;

import com.expense_tracker.userservice.entities.UserInfo;
import com.expense_tracker.userservice.entities.UserInfoDto;
import com.expense_tracker.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    void UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto) {
        Function<UserInfo, UserInfo> updateUser = user -> {
            UserInfo updatedUser = applyUserUpdates(user, userInfoDto);
            return userRepository.save(updatedUser);
        };

        Supplier<UserInfo> createUser = () -> {
            return userRepository.save(userInfoDto.transformToUserInfo());
        };

        UserInfo userInfo = userRepository.findByUserId(userInfoDto.getUserId())
                .map(updateUser)
                .orElseGet(createUser);

        return new UserInfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }

    private UserInfo applyUserUpdates(UserInfo existingUser, UserInfoDto updateRequest) {
        if (updateRequest.getFirstName() != null) {
            existingUser.setFirstName(updateRequest.getFirstName());
        }
        if (updateRequest.getLastName() != null) {
            existingUser.setLastName(updateRequest.getLastName());
        }
        if (updateRequest.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(updateRequest.getPhoneNumber());
        }
        if (updateRequest.getEmail() != null) {
            existingUser.setEmail(updateRequest.getEmail());
        }
        if (updateRequest.getProfilePic() != null) {
            existingUser.setProfilePic(updateRequest.getProfilePic());
        }
        return existingUser;
    }

    public UserInfoDto getUser(String userId) throws Exception {
        Optional<UserInfo> userRes = this.userRepository.findByUserId(userId);
        if(userRes.isEmpty()) throw new Exception("User not found");
        UserInfo user = userRes.get();
        return new UserInfoDto().builder().userId(user.getUserId()).firstName(user.getFirstName()).lastName(user.getLastName()).phoneNumber(user.getPhoneNumber()).email(user.getEmail()).profilePic(user.getProfilePic()).build();
    }
}
