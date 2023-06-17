package com.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.RecipeDAO;
import dao.RecipeDAOImpl;
import entity.Ingredient;
import entity.Recipe;
import exception.ServiceException;
import service.RecipeService;
import service.RecipeServiceImpl;

public class AdminUI {
    private static Scanner scanner = new Scanner(System.in);
    
    private static RecipeService recipeService;

    public static void addRecipe(Scanner scanner) {
        // Code to take recipe details input
    	RecipeDAO rDAO = new RecipeDAOImpl();
    	 recipeService = new RecipeServiceImpl(rDAO);
    	scanner.nextLine();
        System.out.print("Enter recipe name: ");
        String recipeName = scanner.nextLine();
        System.out.print("Enter recipe description: ");
        String recipeDescription = scanner.nextLine();
        System.out.print("Enter preparation steps: ");
        String preparationSteps = scanner.nextLine();
        System.out.println("Enter Number of Ingredients");
        
        int n = scanner.nextInt();
        
        List<Ingredient> recipeIngredients = new ArrayList<>();
        for(int i=0;i<n;i++) {
        	System.out.println("Enter name of Ingredient");
        	String name = scanner.next();
        	Ingredient ig = new Ingredient(name);
        	
        	recipeIngredients.add(ig);
        	
        }
        

        // Code to create Recipe object
        Recipe recipe = new Recipe(recipeName, recipeDescription,  recipeIngredients, preparationSteps);
        
//        System.out.println("recipe" + " " + recipe);

        // Create an object of RecipeService here
        try {
            recipeService.addRecipe(recipe);
            System.out.println("Recipe added successfully");
        } catch (ServiceException | NullPointerException e) {
            System.out.println("Failed to add recipe: " + e.getMessage());
        }
    }

    public static void updateRecipe(Scanner scanner) {
        // Code to take recipe details input
    	 if (recipeService == null) {
    	        recipeService = new RecipeServiceImpl(); // Instantiate the RecipeService implementation class
    	    }

        System.out.print("Enter recipe ID: ");
        int recipeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter recipe name: ");
        String recipeName = scanner.nextLine();
        System.out.print("Enter recipe description: ");
        String recipeDescription = scanner.nextLine();
        System.out.print("Enter preparation steps: ");
        String preparationSteps = scanner.nextLine();
        System.out.println("Enter Number of Ingredients\n");
        
        int n = scanner.nextInt();
        
        List<Ingredient> recipeIngredients = new ArrayList<>();
        for(int i=0;i<n;i++) {
        	System.out.println("Enter name of Ingredient");
        	String name = scanner.next();
        	Ingredient ig = new Ingredient(name);
        	recipeIngredients.add(ig);	
        }
        // Code to create Recipe object
        Recipe recipe = new Recipe(recipeName, recipeDescription, recipeIngredients, preparationSteps);
        recipe.setId(recipeId);
        

        // Create an object of RecipeService here
        try {
            recipeService.updateRecipe(recipe);
            System.out.println("Recipe updated successfully");
        } catch (ServiceException e) {
            System.out.println("Failed to update recipe: " + e.getMessage());
        }
    }

    public static void deleteRecipe(Scanner scanner) {
        System.out.print("Enter recipe ID: ");
        int recipeId = scanner.nextInt();

        // Create an object of RecipeService here
        
        if (recipeService == null) {
	        recipeService = new RecipeServiceImpl(); // Instantiate the RecipeService implementation class
	    }
        
        try {
            recipeService.deleteRecipe(recipeId);
            System.out.println("Recipe deleted successfully");
        } catch (ServiceException e) {
            System.out.println("Failed to delete recipe: " + e.getMessage());
        }
    }

    public static void viewAllRecipes() {
        try {
            RecipeService recipeService = new RecipeServiceImpl(); // Create an instance of RecipeService
            List<Recipe> recipeList = recipeService.getAllRecipes();
            recipeList.forEach(recipe -> System.out.println("ID: " + recipe.getId() + " Name: " + recipe.getName() +
                    " Description: " + recipe.getDescription() + " Preparation Steps: " + recipe.getPreparationSteps()));
        } catch (ServiceException e) {
            System.out.println("Failed to fetch recipes: " + e.getMessage());
        }
    }
    
    public static void searchRecipesByIngredient(Scanner scanner) {
        System.out.print("Enter ingredient name: ");
        String ingredientName = scanner.nextLine();

        try {
            List<Recipe> recipeList = recipeService.searchRecipesByIngredient(ingredientName);
            recipeList.forEach(recipe -> System.out.println("ID: " + recipe.getId() + " Name: " + recipe.getName() +
                    " Description: " + recipe.getDescription() + " Preparation Steps: " + recipe.getPreparationSteps()));
        } catch (ServiceException e) {
            System.out.println("Failed to search recipes by ingredient: " + e.getMessage());
        }
    }
}
