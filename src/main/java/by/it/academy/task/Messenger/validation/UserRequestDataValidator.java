package by.it.academy.task.Messenger.validation;

import by.it.academy.task.Messenger.dto.UserRequest;
import by.it.academy.task.Messenger.enums.UserRole;
import by.it.academy.task.Messenger.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserRequestDataValidator {
    private final UserRepository userRepository;


    public boolean isThePasswordCorrect(String checkPassword) {
        String passwordPattern = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,}";
        return checkPassword.matches(passwordPattern);
    }

    public boolean isUserNameNotTaken(String checkName) {
        return !userRepository.findByUserName(checkName).isPresent();

    }

    public boolean isUserEmailNotTaken(String checkEmail) {
        return !userRepository.findByEmail(checkEmail).isPresent();
    }

    public boolean isUserRoleCorrect(UserRole role) {
        return role == UserRole.ADMIN;
    }

    public boolean isUserByIdExist(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    public boolean isDataCorrect(UserRequest userRequest) {
        Boolean[] validationStatuses = new Boolean[3];

        validationStatuses[0] = isThePasswordCorrect(userRequest.getPassword());
        validationStatuses[1] = isUserEmailNotTaken(userRequest.getEmail());
        validationStatuses[2] = isUserNameNotTaken(userRequest.getUserName());

        boolean finalStatus = true;

        for (Boolean status : validationStatuses) {
            if (status == false) {
                finalStatus = false;
                break;
            }
        }
        return finalStatus;
    }
}
