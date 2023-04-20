package by.it.academy.task.Messenger.repositories;

import by.it.academy.task.Messenger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String userEmail);


}
