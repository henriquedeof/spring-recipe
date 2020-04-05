package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data //creates: getters, setters, toString, equals, constructor
@EqualsAndHashCode(exclude = {"recipes"}) //Methods equals and hashcode are creating a circular reference as this class has a bidirectional relationship.
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ID Being generated automatically.
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();

}
