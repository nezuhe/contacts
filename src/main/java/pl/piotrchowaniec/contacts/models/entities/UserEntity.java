package pl.piotrchowaniec.contacts.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
    private @Column(name = "register_time") LocalDateTime registerTime;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {})
    List<ContactEntity> contacts;


    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserEntity that = (UserEntity) o;
//        return id == that.id &&
//                Objects.equals(login, that.login) &&
//                Objects.equals(password, that.password) &&
//                Objects.equals(registerTime, that.registerTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, login, password, registerTime);
//    }
//
//    @Override
//    public String toString() {
//        return "UserEntity{" +
//                "id=" + id +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", registerTime=" + registerTime +
//                '}';
//    }
}
