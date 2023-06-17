package service;



import java.util.List;

import dao.RecipeDAO;
import dao.RecipeDAOImpl;
import entity.Recipe;
import exception.NoRecordFoundException;
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

    public RecipeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void addRecipe(Recipe recipe) throws ServiceException {
        try {
        	recipeDAO = new RecipeDAOImpl();
//        	System.out.println("Inside addRecipe in RecipeService");
        	recipeDAO.addRecipe(recipe);
//            entityManager.persist(recipe);
            
            
        } catch (Exception e) {
            throw new ServiceException("Failed to add recipe", e);
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) throws ServiceException {
        try {
        	recipeDAO = new RecipeDAOImpl();
//        	System.out.println("Inside addRecipe in RecipeService");
        	recipeDAO.updateRecipe(recipe);
//            entityManager.merge(recipe);
        } catch (Exception e) {
            throw new ServiceException("Failed to update recipe", e);
        }
    }

    @Override
    public void deleteRecipe(int recipeId) throws ServiceException {
        try {
            Recipe recipe = getRecipeById(recipeId);
            if (recipe == null) {
                throw new NoRecordFoundException("Recipe not found with ID: " + recipeId);
            }
            recipeDAO.deleteRecipe(recipe);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete recipe with ID: " + recipeId, e);
        }
    }


    @Override
    public Recipe getRecipeById(int recipeId) throws ServiceException{
        try {
            return recipeDAO.getRecipeById(recipeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get recipe by ID: " + recipeId, e);
        }
    }

    @Override
    public List<Recipe> getAllRecipes() throws ServiceException {
        try {
//        	 System.out.println("In Service of getAllRecipe");
        	 recipeDAO = new RecipeDAOImpl();
//        	 System.out.println("In Service of getAllRecipe");
            return recipeDAO.getAllRecipes();
        } catch (Exception e) {
//        	System.out.println("error In Service");
            throw new ServiceException("Failed to get all recipes", e);
        }
    }

    @Override
    public List<Recipe> searchRecipesByIngredient(String ingredientName) throws ServiceException {
        try {
            return recipeDAO.searchRecipesByIngredients(ingredientName);
        } catch (ServiceException e) {
            throw new ServiceException("Failed to search recipes by ingredient", e);
        }
    }
    
    
}