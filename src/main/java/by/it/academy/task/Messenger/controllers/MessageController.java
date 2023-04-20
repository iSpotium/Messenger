package by.it.academy.task.Messenger.controllers;

import by.it.academy.task.Messenger.dto.MessageRequest;
import by.it.academy.task.Messenger.dto.MessageResponse;
import by.it.academy.task.Messenger.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageServiceImpl;

    @GetMapping("{id}")
    public MessageResponse getMessage(@PathVariable Long id) {
        return messageServiceImpl.getMessage(id);
    }

    @PostMapping("byUser/{userId}")
    public MessageResponse createMessage(@Validated @RequestBody MessageRequest messageRequest, @PathVariable Long userId) {
        return messageServiceImpl.createMessage(messageRequest, userId);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageServiceImpl.deleteMessage(id);
    }

    @GetMapping("all/{userId}")
    public List<MessageResponse> getAllMessages(@PathVariable Long userId, Pageable pageable) {
        return messageServiceImpl.getAllMessages(userId, pageable);
    }

    @PutMapping("update/{id}")
    public MessageResponse updateMessage(@PathVariable Long id, @Validated @RequestBody MessageRequest messageRequest) {
        return messageServiceImpl.updateMessage(id, messageRequest);
    }
}
