package pl.piotrchowaniec.contacts.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    public enum AccountStatus {
        ACTIVATED, BANNED, NOT_ACTIVATED;
    }

    @Id @GeneratedValue private Integer id;
    private String login;

    private String password;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {})
    List<ContactEntity> contacts;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
