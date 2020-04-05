package guru.springframework.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ID Being generated automatically.
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Lob //creating a BLOB field.
    private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") //RECIPE owns the attribute recipe in the INGREDIENT class
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL) //If a RECIPE is deleted, NOTE will be deleted as well.
    private Notes notes;

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);//encapsulating the setting action of Notes "automatically". This way, I do not need to worry about forgetting the bidirectional relationship.
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
