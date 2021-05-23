package IP.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter

@Entity
@Table(name = "needs")
@NamedQueries({

        @NamedQuery(name = "Needs.findAll", query = "SELECT ne FROM Needs ne"),
        @NamedQuery(name = "Needs.findById", query = "SELECT ne FROM Needs ne WHERE ne.id = :id"),
        @NamedQuery(name = "Needs.findByName", query = "SELECT ne FROM Needs ne WHERE ne.name = :name"),
        @NamedQuery(name = "Needs.findByType", query = "SELECT ne FROM Needs ne WHERE ne.type = :type")

})
public class Needs {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public Needs() {
    }

    public Needs(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Needs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Needs needs = (Needs) o;
        return Objects.equals(id, needs.id) && Objects.equals(name, needs.name) && Objects.equals(type, needs.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
