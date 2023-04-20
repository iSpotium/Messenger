package by.it.academy.task.Messenger.mappers;

import by.it.academy.task.Messenger.dto.MessageRequest;
import by.it.academy.task.Messenger.dto.MessageResponse;
import by.it.academy.task.Messenger.entity.Message;
import by.it.academy.task.Messenger.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserMapper userMapper;


    public MessageResponse buildMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .title(message.getTitle())
                .text(message.getText())
                .user(userMapper.buildUserResponse(message.getUser()))
                .build();
    }

    public Message buildMessageByMessageRequest(MessageRequest messageRequest, User user) {

        return Message.builder()
                .title(messageRequest.getTitle())
                .text(messageRequest.getText())
                .user(user)
                .build();

    }
}