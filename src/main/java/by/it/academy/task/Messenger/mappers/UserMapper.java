package by.it.academy.task.Messenger.mappers;

import by.it.academy.task.Messenger.dto.UserRequest;
import by.it.academy.task.Messenger.dto.UserResponse;
import by.it.academy.task.Messenger.entity.User;
import by.it.academy.task.Messenger.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMapper {


    public UserResponse buildUserResponse(User user) {


        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public User buildUserByUserRequest(UserRequest userRequest) {

        return User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role(UserRole.USER)
                .build();
    }

}
