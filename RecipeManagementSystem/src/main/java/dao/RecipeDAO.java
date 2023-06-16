package dao;

import java.util.List;

import entity.Ingredient;
import entity.Recipe;

public interface RecipeDAO {
    void addRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(Recipe recipe);
    Recipe getRecipeById(int recipeId);
    List<Recipe> getAllRecipes();
    List<Recipe> searchRecipesByIngredients(List<Ingredient> ingredients);
    List<Recipe> getTopRatedRecipes(int count);
}

