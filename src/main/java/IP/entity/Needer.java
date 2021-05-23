package IP.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "needer")

@NamedQueries({

        @NamedQuery(name = "Needer.updateIsolated", query = "UPDATE Needer n SET n.isIsolated = :isIsolated WHERE n.idUser = :idUser"),
        @NamedQuery(name = "Needer.findAll", query = "SELECT n FROM Needer n"),
        @NamedQuery(name = "Needer.findById", query = "SELECT n FROM Needer n WHERE n.idUser = :idUser"),
        @NamedQuery(name = "Needer.findByIsolated", query = "SELECT n FROM Needer n WHERE n.isIsolated = :isIsolated"),
        @NamedQuery(name = "Needer.findAllUsers", query = "SELECT u,n from Needer n, User u WHERE n.idUser = u.id"),
        @NamedQuery(name = "Needer.findNeeders", query = "SELECT u, n FROM User u JOIN Needer n ON  (u.id = n.idUser)")})


public class Needer {

    @Id
    @Column(name = "idUser")
    private int idUser;

    @Column(name = "isIsolated")
    private Boolean isIsolated;


    public Needer() {
    }

    public Needer(int id) {
        this.idUser = id;
    }

    @Override
    public String toString() {
        return "Needer{" +
                "idUser=" + idUser +
                ", isIsolated=" + isIsolated +
                '}';
    }
}
