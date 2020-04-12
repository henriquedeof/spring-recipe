package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//There is no annotation on top of the class, therefore, this is a JUnit 5 test.
public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository; //This repository (interface) is being mocked as it's not possible to get information from database. So setting fake information is necessary.

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.recipeService = new RecipeServiceImpl(this.recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(this.recipeRepository.findAll()).thenReturn(recipesData); //Setting the method 'this.recipeRepository.findAll()' with the object 'recipesData'

        Set<Recipe> recipes = this.recipeService.getRecipes();//this.recipeRepository has recipeData's information and this.recipeService can access that.

        assertEquals(1, recipes.size());
        verify(this.recipeRepository, times(1)).findAll();//Verify if the method 'this.recipeRepository.findAll()' is called ONLY ONCE.
    }

    @Test
    void getRecipesById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(this.recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe serviceById = this.recipeService.findById(1L);

        assertEquals(1, serviceById.getId());
        assertNotNull(serviceById);
        verify(this.recipeRepository, times(1)).findById(anyLong());
        verify(this.recipeRepository, never()).findAll();

    }

}
