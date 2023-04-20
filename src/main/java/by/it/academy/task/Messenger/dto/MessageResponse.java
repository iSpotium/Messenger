package by.it.academy.task.Messenger.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {

    private Long id;
    private String title;
    private String text;
    private UserResponse user;
}
