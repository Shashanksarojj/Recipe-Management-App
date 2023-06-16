package dao;

import java.util.List;

import entity.Ingredient;
import entity.Recipe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class RecipeDAOImpl implements RecipeDAO {

   

    @Override
    public void addRecipe(Recipe recipe) {
   	 EntityManager entityManager = null;
     try {
     		entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) {
   	 EntityManager entityManager = null;
     try {
     		entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(recipe);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
   	 EntityManager entityManager = null;
     try {
     		entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(recipe) ? recipe : entityManager.merge(recipe));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Recipe getRecipeById(int id) {
   	 EntityManager entityManager = null;
     try {
     		entityManager = EMUtils.getEntityManager();
            return entityManager.find(Recipe.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Recipe> getAllRecipes() {
   	 EntityManager entityManager = null;
     try {
     		entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Recipe r", Recipe.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Recipe> searchRecipesByIngredients(List<Ingredient> ingredients) {
   	 EntityManager entityManager = null;
     try {
     	entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE i IN :ingredients", Recipe.class);
            query.setParameter("ingredients", ingredients);
            return query.getResultList();
        } finally {
            entityManager.close();
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
