package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService{

    public Set<Recipe> getRecipes();

    public Recipe findById(Long id);

    public RecipeCommand findCommandById(Long id);

    public RecipeCommand saveRecipeCommand(RecipeCommand command);

    public void deleteById(Long id);

}
