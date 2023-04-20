package by.it.academy.task.Messenger.entity;

import by.it.academy.task.Messenger.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 15)
    private String userName;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 30)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Message> messages = new ArrayList<>();

}
