package IP.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter

@Entity
@Table(name = "helper")

@NamedQueries({
        @NamedQuery(name = "Helper.countAvailable", query = "SELECT count(h) from Helper h where h.isAvailable = 1"),
        @NamedQuery(name = "Helper.findHelpers", query = "SELECT u, h FROM User u JOIN Helper h ON  (u.id = h.idUser)"),
        @NamedQuery(name = "Helper.findAll", query = "SELECT h FROM Helper h"),
        @NamedQuery(name = "Helper.findById", query = "SELECT h FROM Helper h WHERE h.idUser = :idUser"),
        @NamedQuery(name = "Helper.findByAvailable", query = "SELECT h FROM Helper h WHERE h.isAvailable = :isAvailable"),
        @NamedQuery(name = "Helper.findByDistance", query = "SELECT h FROM Helper h WHERE h.distanceAccepted = :distanceAccepted"),
        @NamedQuery(name = "Helper.updateDistance", query = "UPDATE Helper h SET h.distanceAccepted = :distanceAccepted WHERE h.idUser = :idUser"),
        @NamedQuery(name = "Helper.updateIsAvailable", query = "UPDATE Helper h SET h.isAvailable = :isAvailable WHERE h.idUser = :idUser")
        })

public class Helper {

    @Id
    @Column(name = "idUser")
    private int idUser;

    @Column(name = "distanceAccepted")
    private int distanceAccepted;

    @Column(name = "isAvailable")
    private int isAvailable;


    public Helper() {
    }

    public Helper(int id) {
        this.idUser = id;
    }

    public Helper(int id, int distanceAccepted) {
        this.idUser = id;
        this.distanceAccepted = distanceAccepted;
    }

    @Override
    public String toString() {
        return "Helper{" +
                "idUser=" + idUser +
                ", distanceAccepted=" + distanceAccepted +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Helper helper = (Helper) o;
        return idUser == helper.idUser && Double.compare(helper.distanceAccepted, distanceAccepted) == 0 && isAvailable == helper.isAvailable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, distanceAccepted, isAvailable);
    }
}
