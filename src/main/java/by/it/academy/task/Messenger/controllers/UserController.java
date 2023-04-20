package by.it.academy.task.Messenger.controllers;

import by.it.academy.task.Messenger.dto.UserRequest;
import by.it.academy.task.Messenger.dto.UserResponse;
import by.it.academy.task.Messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userServiceImpl;


    @GetMapping("{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userServiceImpl.getUser(id);
    }

    @PostMapping("registration")
    public UserResponse createUser(@RequestBody UserRequest loginForm) {
        return userServiceImpl.createUser(loginForm);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
    }

    @GetMapping("getByUserName/{userName}")
    public UserResponse getUserByName(@PathVariable String userName) {
        return userServiceImpl.getUserByName(userName);
    }

    @GetMapping("getByUserEmail/{userEmail}")
    public UserResponse getUserByEmail(@PathVariable String userEmail) {
        return userServiceImpl.getUserByEmail(userEmail);
    }

    @GetMapping("all/{id}")
    public List<UserResponse> getAllUsers(@PathVariable Long id, Pageable pageable) {
        return userServiceImpl.getAllUsers(id, pageable);
    }

    @PutMapping("update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userServiceImpl.userChangeData(id, userRequest);
    }
}