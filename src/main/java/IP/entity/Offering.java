package IP.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter

@Entity
@Table(name = "offering")

@NamedQueries({
        @NamedQuery(name = "Offering.sumQuantity", query = "SELECT SUM(quantity) from Offering group by idHelper having idHelper=:idHelper"),
        @NamedQuery(name = "Offering.findProductInfo", query = "SELECT o FROM Offering o WHERE o.quantity > -1 AND o.idHelper = :idHelper"),
        @NamedQuery(name = "Offering.findServiceInfo", query = "SELECT o FROM Offering o WHERE o.quantity = -1 AND o.idHelper = :idHelper"),
        @NamedQuery(name = "Offering.findAll", query = "SELECT o FROM Offering o"),
        @NamedQuery(name = "Offering.findByIdHelper", query = "SELECT o FROM Offering o WHERE o.idHelper = :idHelper"),
        @NamedQuery(name = "Offering.findByIdNeeds", query = "SELECT o FROM Offering o WHERE o.idNeeds = :idNeeds"),
        @NamedQuery(name = "Offering.removeHelper", query = "DELETE FROM Offering o WHERE o.idHelper = :idHelper"),
        @NamedQuery(name = "Offering.joinNeeds", query = "SELECT ne, o FROM Needs ne JOIN Offering o ON  (ne.id = o.idNeeds)"),
        @NamedQuery(name = "Offering.joinHelper", query = "SELECT h, o FROM Helper h JOIN Offering o ON  (h.idUser = o.idHelper)"),
        @NamedQuery(name = "Offering.joinHelperUser", query = "SELECT u, h, o FROM User u JOIN Helper h ON  (u.id = h.idUser) JOIN Offering o ON (h.idUser = o.idHelper)")
})
public class Offering implements Serializable {
    @Id
    @Column(name = "idHelper")
    private int idHelper;

    @Id
    @Column(name = "idNeeds")
    private int idNeeds;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "details")
    private String details;


    public Offering() {
    }

    public Offering(int idHelper, int idNeeds, int quantity) {
        this.idHelper = idHelper;
        this.idNeeds = idNeeds;
        this.quantity = quantity;
    }

    public Offering(int idHelper, int idNeeds, String details) {
        this.idHelper = idHelper;
        this.idNeeds = idNeeds;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Offering{" +
                "idHelper=" + idHelper +
                ", idNeeds=" + idNeeds +
                ", quantity=" + quantity +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offering offering = (Offering) o;
        return idHelper == offering.idHelper && idNeeds == offering.idNeeds && quantity == offering.quantity && Objects.equals(details, offering.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHelper, idNeeds, quantity, details);
    }
}