package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;

//@ExtendWith(MockitoExtension.class) //I can user this annotation or the method MockitoAnnotations.initMocks(this);
class IndexControllerTest {

    private IndexController indexController;

    @Mock
    private Model model;

    @Mock
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); //I can use this method or the annotation @ExtendWith(MockitoExtension.class)
        this.indexController = new IndexController(this.recipeService);
    }

    @Test
    void getIndexPage() {
        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setDescription("Adding description");
        recipes.add(recipe);

        Mockito.when(this.recipeService.getRecipes()).thenReturn(recipes);//setting recipes into this.recipeService.getRecipes()
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);//Creating a Set that I can use to my assertions

        //when
        String indexPage = this.indexController.getIndexPage(this.model);

        //then
        Assertions.assertEquals("index", indexPage);
        Mockito.verify(this.recipeService, Mockito.times(1)).getRecipes();

        //Mockito.verify(this.model, Mockito.times(1)).addAttribute(eq("recipes"), ArgumentMatchers.anySet());
        //using this line instead of the line above. This allows me to have an 'ARGUMENT' that I can use and have control of it.
        Mockito.verify(this.model, Mockito.times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> recipeSet = argumentCaptor.getValue();
        Assertions.assertEquals(2, recipeSet.size());

    }

    @Test
    void testMockMvc() throws Exception {
        //A way of testing Spring MVC Controllers in a standalone mode, without bringing all the system and Spring Context up.
        //It is still a unit test, regarding MVC Controllers.

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }





}
