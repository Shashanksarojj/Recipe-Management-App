package entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int recipeId;
	    
      private String recipeName;
	    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Ingredient> ingredients;
	
}
