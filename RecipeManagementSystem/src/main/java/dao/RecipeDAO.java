package dao;

import java.util.List;

import entity.Ingredient;
import entity.Recipe;
import exception.ServiceException;

public interface RecipeDAO {
    void addRecipe(Recipe recipe);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(Recipe recipe) throws ServiceException;
    Recipe getRecipeById(int recipeId) throws ServiceException;
    List<Recipe> getAllRecipes()throws ServiceException ;
    List<Recipe> searchRecipesByIngredients(String ingredientName) throws ServiceException;
    List<Recipe> getTopRatedRecipes(int count);
}

