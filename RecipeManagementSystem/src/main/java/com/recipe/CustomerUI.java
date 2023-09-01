package com.recipe;

import java.util.List;
import java.util.Scanner;

import entity.Recipe;
import exception.ServiceException;
import service.CustomerService;
import service.RecipeService;
import service.RecipeServiceImpl;

public class CustomerUI {

    private static Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService; // Initialize this based on your setup
    private static RecipeService recipeService;
    
    public static void userMenu(Scanner scanner) {
        int choice = 0;
        do {
            displayUserMenu();
            System.out.print("Enter selection: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    // Implement your recipe view or other functionality
                	viewAllRecipes();
                    break;
                case 2:
                    // Implement other customer-related actions
                	searchRecipesByIngredient(scanner);
                	
                    break;
                case 3:
                    // Implement other customer-related actions
//                	searchRecipesByIngredient(scanner);
                	
                    break;
                case 0:
                    System.out.println("Logged out");
                    break;
                default:
                    System.out.println("Invalid selection, try again");
            }
        } while (choice != 0);
    }
    
    private static void displayUserMenu() {
        System.out.println("1. View Recipes");
        System.out.println("2. Search By Ingredients");
        System.out.println("2. Other Actions");
        System.out.println("0. Logout");
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
