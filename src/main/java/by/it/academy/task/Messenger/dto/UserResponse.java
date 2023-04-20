package by.it.academy.task.Messenger.dto;

import by.it.academy.task.Messenger.enums.UserRole;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponse {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private UserRole role;
}
