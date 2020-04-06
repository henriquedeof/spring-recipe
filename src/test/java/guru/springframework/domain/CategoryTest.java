package guru.springframework.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    private Category category;

   @BeforeEach //@AfterEach
   public void setUp(){
       this.category = new Category();
       System.out.println("=============== Set Up method() ================");
   }

    @Test
    void getId() {
        Long idValue = 4L;
        this.category.setId(idValue);
        Assertions.assertEquals(idValue, this.category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}
