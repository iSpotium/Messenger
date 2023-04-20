package by.it.academy.task.Messenger.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}