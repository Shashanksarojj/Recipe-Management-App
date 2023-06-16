package service;



import java.util.List;

import dao.RecipeDAO;
import entity.Recipe;
import exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
public class RecipeServiceImpl implements RecipeService {

    @PersistenceContext
    private EntityManager entityManager;

    private RecipeDAO recipeDAO;

    public RecipeServiceImpl(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @Override
    public void addRecipe(Recipe recipe) throws ServiceException {
        try {
            entityManager.persist(recipe);
        } catch (Exception e) {
            throw new ServiceException("Failed to add recipe", e);
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) throws ServiceException {
        try {
            entityManager.merge(recipe);
        } catch (Exception e) {
            throw new ServiceException("Failed to update recipe", e);
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) throws ServiceException {
        try {
            entityManager.remove(recipe);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete recipe", e);
        }
    }

    @Override
    public Recipe getRecipeById(long recipeId) throws ServiceException {
        try {
            return entityManager.find(Recipe.class, recipeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get recipe by ID", e);
        }
    }

    @Override
    public List<Recipe> getAllRecipes() throws ServiceException {
        try {
            return entityManager.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all recipes", e);
        }
    }

    @Override
    public List<Recipe> searchRecipesByIngredient(String ingredientName) throws ServiceException {
        try {
            // TODO: Implement the search logic using the entityManager or other necessary methods
            throw new ServiceException("Search recipes by ingredient is not implemented yet");
        } catch (Exception e) {
            throw new ServiceException("Failed to search recipes by ingredient", e);
        }
    }
}