package by.it.academy.task.Messenger.services;

import by.it.academy.task.Messenger.dto.UserRequest;
import by.it.academy.task.Messenger.dto.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserResponse getUser(Long userId);

    UserResponse createUser(UserRequest userRequest);

    UserResponse userChangeData(Long id, UserRequest userRequest);

    List<UserResponse> getAllUsers(Long userId, Pageable pageable);

    void deleteUser(Long userId);

    UserResponse getUserByName(String userName);

    UserResponse getUserByEmail(String userEmail);
}
