package service;

import java.util.List;

import entity.Recipe;
import exception.ServiceException;

public interface RecipeService {
    void addRecipe(Recipe recipe) throws ServiceException;
    void updateRecipe(Recipe recipe) throws ServiceException;
    void deleteRecipe(Recipe recipe) throws ServiceException;
    Recipe getRecipeById(long recipeId) throws ServiceException;
    List<Recipe> getAllRecipes() throws ServiceException;
    List<Recipe> searchRecipesByIngredient(String ingredientName) throws ServiceException;
}
