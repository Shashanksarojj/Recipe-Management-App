package dao;

import java.util.List;

import entity.Ingredient;

public interface IngredientDAO {

	void addIngredient(Ingredient ingredient);
    void updateIngredient(Ingredient ingredient);
    void deleteIngredient(Ingredient ingredient);
    Ingredient getIngredientById(int id);
    List<Ingredient> getAllIngredients();
}
