package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {
   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientId;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    
    // Other attributes, constructors, getters, and setters
    
    public Ingredient(String name) {
		super();
		this.name = name;
	}
	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIngredientId() {
        return ingredientId;
    }
    
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Recipe getRecipe() {
        return recipe;
    }
    
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }



	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", name=" + name + "]";
	}
    
}
