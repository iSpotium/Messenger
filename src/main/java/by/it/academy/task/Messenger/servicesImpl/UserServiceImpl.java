package by.it.academy.task.Messenger.servicesImpl;

import by.it.academy.task.Messenger.dto.UserRequest;
import by.it.academy.task.Messenger.dto.UserResponse;
import by.it.academy.task.Messenger.entity.User;
import by.it.academy.task.Messenger.exceptions.IncorrectDataException;
import by.it.academy.task.Messenger.exceptions.NoAccessException;
import by.it.academy.task.Messenger.exceptions.NotFoundException;
import by.it.academy.task.Messenger.mappers.UserMapper;
import by.it.academy.task.Messenger.repositories.UserRepository;
import by.it.academy.task.Messenger.services.UserService;
import by.it.academy.task.Messenger.validation.UserRequestDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRequestDataValidator userRequestDataValidator;
    private final UserRepository userRepositoryImpl;


    @Override
    public UserResponse getUser(Long userId) {
        return userRepositoryImpl.findById(userId)
                .map(userMapper::buildUserResponse)
                .orElseThrow(() -> new NotFoundException(String.format("User by id %s does not exist", userId)));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if (!userRequestDataValidator.isDataCorrect(userRequest)) {
            throw new IncorrectDataException("Incorrect data entered");
        } else {
            User user = userMapper.buildUserByUserRequest(userRequest);
            User savedUser = userRepositoryImpl.save(user);
            return userMapper.buildUserResponse(savedUser);
        }
    }

    @Override
    public UserResponse userChangeData(Long id, UserRequest userRequest) {
        User updateUser = userRepositoryImpl.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!userRequestDataValidator.isDataCorrect(userRequest)) {
            throw new IncorrectDataException("Incorrect data entered");
        } else {
            updateUser.setUserName(userRequest.getUserName());
            updateUser.setEmail(userRequest.getEmail());
            updateUser.setPassword(userRequest.getPassword());
            User saveUser = userRepositoryImpl.save(updateUser);
            return userMapper.buildUserResponse(saveUser);
        }
    }


    @Override
    public List<UserResponse> getAllUsers(Long id, Pageable pageable) {
        User findUser = userRepositoryImpl.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!userRequestDataValidator.isUserRoleCorrect(findUser.getRole())) {
            throw new NoAccessException("No admin rights");
        } else {
            return userRepositoryImpl.findAll(pageable)
                    .stream()
                    .map(userMapper::buildUserResponse)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void deleteUser(Long userId) {

        if (userRequestDataValidator.isUserByIdExist(userId)) {
            userRepositoryImpl.deleteById(userId);
        } else {
            throw new NotFoundException(String.format("User by id %s does not exist", userId));
        }
    }

    @Override
    public UserResponse getUserByName(String userName) {
        return userRepositoryImpl.findByUserName(userName)
                .map(userMapper::buildUserResponse)
                .orElseThrow(() -> new NotFoundException(String.format("Can not find User by name %s", userName)));
    }

    @Override
    public UserResponse getUserByEmail(String userEmail) {
        return userRepositoryImpl.findByEmail(userEmail)
                .map(userMapper::buildUserResponse)
                .orElseThrow(() -> new NotFoundException(String.format("Can not find User by Email %s", userEmail)));
    }
}
