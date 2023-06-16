package dao;

import java.util.List;

import entity.Ingredient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class IngredientDAOImpl implements IngredientDAO {

  

    @Override
    public void addIngredient(Ingredient ingredient) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(ingredient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(ingredient);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(ingredient) ? ingredient : entityManager.merge(ingredient));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Ingredient getIngredientById(int id) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            return entityManager.find(Ingredient.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT i FROM Ingredient i", Ingredient.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
  
}

