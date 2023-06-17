package dao;

import entity.Recipe;

public interface RecipeRepository {
	
	 Recipe findById(int recipeId);
	    void save(Recipe recipe);
	    void update(Recipe recipe);
	    void delete(Recipe recipe);
	    
	    
//	    
//	    List<Recipe> findAll();
//	    List<Recipe> searchByName(String keyword);
//	    List<Recipe> searchByIngredient(String ingredientName);
//	    List<Recipe> getTopRatedRecipes(int limit);
//	    List<Recipe> getLatestRecipes(int limit);
}
