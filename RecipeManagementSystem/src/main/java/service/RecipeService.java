package service;

import java.util.List;

import entity.Recipe;
import exception.ServiceException;

public interface RecipeService {
    void addRecipe(Recipe recipe) throws ServiceException;
    void updateRecipe(Recipe recipe) throws ServiceException;
    void deleteRecipe(int recipeId) throws ServiceException;
    Recipe getRecipeById(int recipeId) throws ServiceException;
    List<Recipe> getAllRecipes() throws ServiceException;
    List<Recipe> searchRecipesByIngredient(String ingredientName) throws ServiceException;
}
