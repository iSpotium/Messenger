package by.it.academy.task.Messenger.repositories;

import by.it.academy.task.Messenger.entity.Message;
import by.it.academy.task.Messenger.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<List<Message>> findMessagesByUser(User user, Pageable pageable);
}
