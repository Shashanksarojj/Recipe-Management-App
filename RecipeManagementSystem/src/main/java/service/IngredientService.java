package service;

import java.util.List;

import entity.Ingredient;
import exception.ServiceException;

public interface IngredientService {
	 	void addIngredient(Ingredient ingredient) throws ServiceException;
	    void updateIngredient(Ingredient ingredient) throws ServiceException;
	    void deleteIngredient(Ingredient ingredient) throws ServiceException;
	    Ingredient getIngredientById(int id) throws ServiceException;
	    List<Ingredient> getAllIngredients() throws ServiceException;
}
