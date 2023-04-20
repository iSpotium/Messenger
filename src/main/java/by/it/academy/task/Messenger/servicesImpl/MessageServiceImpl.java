package by.it.academy.task.Messenger.servicesImpl;

import by.it.academy.task.Messenger.dto.MessageRequest;
import by.it.academy.task.Messenger.dto.MessageResponse;
import by.it.academy.task.Messenger.entity.Message;
import by.it.academy.task.Messenger.entity.User;
import by.it.academy.task.Messenger.exceptions.NotFoundException;
import by.it.academy.task.Messenger.mappers.MessageMapper;
import by.it.academy.task.Messenger.repositories.MessageRepository;
import by.it.academy.task.Messenger.repositories.UserRepository;
import by.it.academy.task.Messenger.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    private final UserRepository userRepositoryImpl;
    private final MessageRepository messageRepositoryImpl;

    @Override
    public MessageResponse getMessage(Long id) {
        return messageRepositoryImpl.findById(id)
                .map(messageMapper::buildMessageResponse)
                .orElseThrow(() -> new NotFoundException(String.format("Message by id %s does not exist", id)));
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepositoryImpl.deleteById(id);
    }

    @Override
    public MessageResponse createMessage(MessageRequest messageRequest, Long userId) {
        User findUser = userRepositoryImpl.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Message newMessage = messageMapper.buildMessageByMessageRequest(messageRequest, findUser);
        Message savedMessage = messageRepositoryImpl.save(newMessage);
        return messageMapper.buildMessageResponse(savedMessage);
    }


    @Override
    public MessageResponse updateMessage(Long id, MessageRequest messageRequest) {
        Message updateMessage = messageRepositoryImpl.findById(id)
                .orElseThrow(() -> new NotFoundException("Message not found"));

        updateMessage.setTitle(messageRequest.getTitle());
        updateMessage.setText(messageRequest.getText());
        Message saveMessage = messageRepositoryImpl.save(updateMessage);
        return messageMapper.buildMessageResponse(saveMessage);
    }


    @Override
    public List<MessageResponse> getAllMessages(Long userId, Pageable pageable) {
        User findUser = userRepositoryImpl.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<Message> messages = messageRepositoryImpl.findMessagesByUser(findUser, pageable).get();
        return messages
                .stream()
                .map(messageMapper::buildMessageResponse)
                .collect(Collectors.toList());
    }

}