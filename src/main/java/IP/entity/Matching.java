package IP.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter

@Entity
@Table(name = "matching")

@NamedQueries({

        @NamedQuery(name = "Matching.findAll", query = "SELECT m FROM Matching m"),
        @NamedQuery(name = "Matching.findByUsernameNeeder", query = "SELECT m FROM Matching m WHERE m.usernameNeeder = :usernameNeeder"),
        @NamedQuery(name = "Matching.findByUsernameHelper", query = "SELECT m FROM Matching m WHERE m.usernameHelper = :usernameHelper"),
        @NamedQuery(name = "Matching.findByUsername", query = "SELECT m FROM Matching m WHERE m.usernameHelper = :username OR m.usernameNeeder = :username"),
        @NamedQuery(name = "Matching.findByDate", query = "SELECT m FROM Matching m WHERE m.dateMatching = :dateMatching"),
        @NamedQuery(name = "Matching.removeUsername", query = "DELETE FROM Matching m WHERE m.usernameHelper = :username OR m.usernameNeeder = :username"),
        @NamedQuery(name = "Matching.removeDate", query = "DELETE FROM Matching m WHERE m.dateMatching = :dateMatching")})


public class Matching {

    @Id

    @Column(name = "ID")
    private Integer ID;

    @Column(name = "usernameNeeder")
    private String usernameNeeder;

    @Column(name = "usernameHelper")
    private String usernameHelper;

    @Column(name = "dateMatching")
    private String dateMatching;

    public Matching() {
    }

    public Matching(int ID, String usernameNeeder, String usernameHelper, String dateMatching) {
        this.ID = ID;
        this.usernameNeeder = usernameNeeder;
        this.usernameHelper = usernameHelper;
        this.dateMatching = dateMatching;
    }

    @Override
    public String toString() {
        return "Matching{" +
                "ID=" + ID +
                ", usernameNeeder='" + usernameNeeder + '\'' +
                ", usernameHelper='" + usernameHelper + '\'' +
                ", dateMatching='" + dateMatching + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matching matching = (Matching) o;
        return Objects.equals(ID, matching.ID) && Objects.equals(usernameNeeder, matching.usernameNeeder) && Objects.equals(usernameHelper, matching.usernameHelper) && Objects.equals(dateMatching, matching.dateMatching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, usernameNeeder, usernameHelper, dateMatching);
    }
}
