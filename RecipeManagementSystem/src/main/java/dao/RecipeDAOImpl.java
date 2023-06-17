package dao;

import java.util.List;

import entity.Recipe;
import exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class RecipeDAOImpl implements RecipeDAO {

    private EntityManagerFactory emf;

    public RecipeDAOImpl() {
        emf = Persistence.createEntityManagerFactory("ConstructWeekSB101");
    }

    @Override
    public void addRecipe(Recipe recipe) {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Recipe getRecipeById(int recipeId) throws ServiceException{
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            return entityManager.find(Recipe.class, recipeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get recipe by ID: " + recipeId, e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) throws ServiceException {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(recipe) ? recipe : entityManager.merge(recipe));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new ServiceException("Failed to delete recipe", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Recipe> getAllRecipes() throws ServiceException {
//    	 System.out.println("In DAO of getAllRecipes");
        EntityManager entityManager = null;
        List<Recipe> recipeList = null;
        try {
            entityManager = emf.createEntityManager();
           
            Query query = entityManager.createQuery("SELECT r FROM recipes r");
            recipeList = query.getResultList();
            System.out.println("Retrieved " + recipeList.size() + " recipes");
            return recipeList;
        } catch (Exception e) {
            throw new ServiceException("Failed to get all recipes", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }



    @Override
    public List<Recipe> searchRecipesByIngredients(String ingredientName) throws ServiceException {
        EntityManager entityManager = null;
        try {
            entityManager = emf.createEntityManager();
            TypedQuery<Recipe> query = entityManager.createQuery("SELECT * FROM Recipe r JOIN r.ingredients i WHERE i.name = :ingredientName", Recipe.class);
            query.setParameter("ingredientName", ingredientName);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException ("Failed to search recipes by ingredient", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Recipe> getTopRatedRecipes(int count) {
   	 EntityManager entityManager = null;
     try {
    	
     		entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Recipe r ORDER BY r.rating DESC", Recipe.class);
            query.setMaxResults(count);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
   
}
