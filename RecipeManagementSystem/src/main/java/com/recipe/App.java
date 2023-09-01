package com.recipe;

import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import entity.Customer;
import exception.SomethingWentWrongException;
import service.CustomerService;
import service.CustomerServiceImpl;


public class App 
{	
	
	static void displayAdminMenu() {
		System.out.println("1. Add Recipe");
        System.out.println("2. Update Recipe");
        System.out.println("3. Delete Recipe");
        System.out.println("4. View All Recipes");
        System.out.println("5. Search RecipesByIngredient");
//        System.out.println("6. Update Category");
//        System.out.println("7. Delete Category");
//        System.out.println("8. Search Recipes by Ingredient");
        System.out.println("0. Logout");
	}
	
	static void adminMenu(Scanner sc) {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
    		switch(choice) {
    			case 1:
    				AdminUI.addRecipe(sc);
//    				System.out.println("Selected 1");
    				break;
    			case 2:
    				AdminUI.updateRecipe(sc);
    				break;
    			case 3:
    				AdminUI.deleteRecipe(sc);
    				
    				break;
    			case 4:
    				AdminUI.viewAllRecipes();
    				break;
    			case 5:
//    				AdminUI.viewPlan();
    				AdminUI.searchRecipesByIngredient(sc);
    				break;
//    			case 6:
//    				AdminUI.updatePlan(sc);
//    				break;
//    			case 7:
//    				AdminUI.updatePremiumAndSurcharge(sc);
//    				break;
//    			case 8:
//    				AdminUI.viewAllCustomers();
//    				break;
    			
    			case 0:
    				System.out.println("Bye Bye Admin");
    				break;
    			default:
    				System.out.println("Invalid Selection, try again");
    		}
    	}while(choice != 0);	
	}
	static void adminLogin(Scanner sc) {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		if(username.equals("a") && password.equals("a")) {
			adminMenu(sc);
		}else {
			System.out.println("Invalid Username of Password");
		}
	}
    public static void main( String[] args )
    {
    	Scanner sc = new Scanner(System.in);
//    	sc.nextLine();
    	  int choice = 0;
          do {
              System.out.println("1. Admin Login");
              System.out.println("2. User Login");
              System.out.println("3. User Registration");
              System.out.println("0. Exit");
              System.out.print("Enter Selection: ");
              choice = sc.nextInt();
              switch (choice) {
                  case 1:
                      adminLogin(sc);
                      break;
                  case 2:
                      userLogin(sc);
                      break;
                  case 3:
                      userRegistration(sc);
                      break;
                  case 0:
                      System.out.println("Thank you for using the Recipe Management System!");
                      break;
                  default:
                      System.out.println("Invalid Selection, please try again");
              }
          } while (choice != 0);
          
          sc.close();
    
    }

    private static void userLogin(Scanner sc) {
        // TODO: Implement user login functionality
        System.out.print("Enter username: ");
        sc.nextLine();
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        CustomerDAO customerDAO = new CustomerDAOImpl(); // Create the DAO instance
		 CustomerService customerService = new CustomerServiceImpl(customerDAO); // Pass the DAO instance
		 Customer customer = customerService.getCustomerByUsername(username);

		if (BCrypt.checkpw(password, customer.getPassword())) {
		    System.out.println("Login successful!");
		    // Implement your user menu or functionality here
		    
		    CustomerUI.userMenu(sc);
		} else {
		    System.out.println("Invalid password");
		}
    }

    private static void userRegistration(Scanner sc) {
        // TODO: Implement user registration functionality
    	
    	  System.out.print("Enter name: ");
    	  sc.nextLine();
          String name = sc.nextLine();
          
          System.out.print("Enter username: ");
          String username = sc.nextLine();
          
          System.out.print("Enter password: ");
          String password = sc.nextLine();
          
          
          
          
          String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

          // Create and register the customer
          Customer customer = new Customer(name, username, hashedPassword);
          
          CustomerDAO customerDAO = new CustomerDAOImpl(); // Create the DAO instance
 		  CustomerService customerService = new CustomerServiceImpl(customerDAO); 
          customerService.registerCustomer(customer);
          
          System.out.println("Registration successful!");
    }
}
