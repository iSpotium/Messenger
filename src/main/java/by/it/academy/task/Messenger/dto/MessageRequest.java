package by.it.academy.task.Messenger.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
@Builder
public class MessageRequest {

    @NotBlank
    private String title;
    private String text;
}
