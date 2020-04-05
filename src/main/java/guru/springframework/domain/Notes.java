package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"}) //Methods equals and hashcode are creating a circular reference as this class has a bidirectional relationship.
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne //I did not add cascade here because I do not want to delete a RECIPE if a NOTES object is deleted.
    private Recipe recipe;

    @Lob //Annotation for large objects. By default, the quantity of characters for JPA and Hibernate is 255. In this case, there is the possibility to have more than that.
    private String recipeNotes;

}
