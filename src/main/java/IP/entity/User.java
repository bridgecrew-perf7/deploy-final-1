package IP.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Objects;

@Setter
@Getter

@Entity
@Table(name = "users")

@NamedQueries({

        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
        @NamedQuery(name = "User.findByStartHour", query = "SELECT u FROM User u WHERE u.startHour = :startHour"),
        @NamedQuery(name = "User.findByFinalHour", query = "SELECT u FROM User u WHERE u.finalHour = :finalHour"),
        @NamedQuery(name = "User.remove", query = "DELETE FROM User u WHERE u.id = :id")
})


public  class User {

    @Id

    @Column(name = "id")
    private Integer id;

    @Column(name = "userName")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String firstName;

    @Column(name = "surname")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String telephone;

    @Column(name = "email")
    private String mail;

    @Column(name = "startHour")
    private Time startHour;

    @Column(name = "finalHour")
    private Time finalHour;

    public User() {
    }

    public  User(int id)
    {this.id = id;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", startHour=" + startHour +
                ", finalHour=" + finalHour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(address, user.address) && Objects.equals(telephone, user.telephone) && Objects.equals(mail, user.mail) && Objects.equals(startHour, user.startHour) && Objects.equals(finalHour, user.finalHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, address, telephone, mail, startHour, finalHour);
    }
}

