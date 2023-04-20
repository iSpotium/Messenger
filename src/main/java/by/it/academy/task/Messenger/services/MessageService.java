package by.it.academy.task.Messenger.services;

import by.it.academy.task.Messenger.dto.MessageRequest;
import by.it.academy.task.Messenger.dto.MessageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    MessageResponse getMessage(Long postId);

    void deleteMessage(Long postId);

    MessageResponse createMessage(MessageRequest messageRequest, Long userId);

    MessageResponse updateMessage(Long postId, MessageRequest messageRequest);

    List<MessageResponse> getAllMessages(Long userId, Pageable pageable);


}
