package pl.piotrchowaniec.contacts.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String surname;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return id + ". " +
                name + " " +
                surname + ", " +
                phone + ", " +
                email;
    }
}
