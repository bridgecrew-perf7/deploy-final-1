package IP.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter

@Entity
@Table(name = "request")

@NamedQueries({
        @NamedQuery(name = "Request.countUnresolved", query = "SELECT distinct r.idNeeder from Request r"),
        @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
        @NamedQuery(name = "Request.findNeedsNeeder", query = "SELECT r FROM Request r WHERE r.idNeeder = :idNeeder AND r.idNeeds = :idNeeds"),
        @NamedQuery(name = "Request.findByIdNeeder", query = "SELECT r FROM Request r WHERE r.idNeeder = :idNeeder"),
        @NamedQuery(name = "Request.findByIdNeeds", query = "SELECT r FROM Request r WHERE r.idNeeds = :idNeeds"),
        @NamedQuery(name = "Request.findProductInfo", query = "SELECT r FROM Request r WHERE r.quantity > -1 AND r.idNeeder = :idNeeder"),
        @NamedQuery(name = "Request.findServiceInfo", query = "SELECT r FROM Request r WHERE r.quantity = -1 AND r.idNeeder = :idNeeder"),
        @NamedQuery(name = "Request.removeNeeder", query = "DELETE FROM Request r WHERE r.idNeeder = :idNeeder"),
        @NamedQuery(name = "Request.joinNeeds", query = "SELECT ne, r FROM Needs ne JOIN Request r ON  (ne.id = r.idNeeds)"),
        @NamedQuery(name = "Request.joinNeeder", query = "SELECT n, r FROM Needer n JOIN Request r ON  (n.idUser = r.idNeeder)"),
        @NamedQuery(name = "Request.joinNeederUser", query = "SELECT u, n, r FROM User u JOIN Needer n ON  (u.id = n.idUser) JOIN Request r ON (n.idUser = r.idNeeder)")

})


public class Request implements Serializable {
    @Id
    @Column(name = "idNeeder")
    private int idNeeder;

    @Id
    @Column(name = "idNeeds")
    private int idNeeds;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "details")
    private String details;


    public Request() {
    }

    public Request(int idNeeder, int idNeeds) {
        this.idNeeder = idNeeder;
        this.idNeeds = idNeeds;
    }

    public Request(int idNeeder, int idNeeds, int quantity) {
        this.idNeeder = idNeeder;
        this.idNeeds = idNeeds;
        this.quantity = quantity;
    }

    public Request(int idNeeder, int idNeeds, String details) {
        this.idNeeder = idNeeder;
        this.idNeeds = idNeeds;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Request{" +
                "idNeeder=" + idNeeder +
                ", idNeeds=" + idNeeds +
                ", quantity=" + quantity +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return idNeeder == request.idNeeder && idNeeds == request.idNeeds && quantity == request.quantity && Objects.equals(details, request.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNeeder, idNeeds, quantity, details);
    }
}
