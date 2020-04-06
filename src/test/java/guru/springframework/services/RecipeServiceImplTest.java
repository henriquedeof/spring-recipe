package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

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
        System.out.println("this.recipeService.getRecipes().size() -> " + recipes.size());

        Assertions.assertEquals(1, recipes.size());
        verify(this.recipeRepository, times(1)).findAll();//Verify if the method 'this.recipeRepository.findAll()' is called ONLY ONCE.
    }
}