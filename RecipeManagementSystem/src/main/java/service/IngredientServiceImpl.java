package service;

import java.util.List;

import dao.IngredientDAO;
import entity.Ingredient;
import exception.ServiceException;

public class IngredientServiceImpl implements IngredientService {

    private IngredientDAO ingredientDAO;

    public IngredientServiceImpl(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public void addIngredient(Ingredient ingredient) throws ServiceException {
        try {
            ingredientDAO.addIngredient(ingredient);
        } catch (Exception e) {
            throw new ServiceException("Failed to add ingredient.");
        }
    }

    @Override
    public void updateIngredient(Ingredient ingredient) throws ServiceException {
        try {
            ingredientDAO.updateIngredient(ingredient);
        } catch (Exception e) {
            throw new ServiceException("Failed to update ingredient.");
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) throws ServiceException {
        try {
            ingredientDAO.deleteIngredient(ingredient);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete ingredient.");
        }
    }

    @Override
    public Ingredient getIngredientById(int id) throws ServiceException {
        try {
            return ingredientDAO.getIngredientById(id);
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve ingredient.");
        }
    }

    @Override
    public List<Ingredient> getAllIngredients() throws ServiceException {
        try {
            return ingredientDAO.getAllIngredients();
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve ingredients.");
        }
    }
}