package pl.piotrchowaniec.contacts.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class ContactEntity {
    @Id @GeneratedValue private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String email;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public ContactEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactEntity that = (ContactEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone, email);
    }

    @Override
    public String toString() {
        return id + ". " +
                name + " " +
                surname + ", " +
                phone + ", " +
                email;
    }
}
