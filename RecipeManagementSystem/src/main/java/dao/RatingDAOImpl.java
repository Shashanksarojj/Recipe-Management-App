package dao;

import java.util.List;

import entity.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class RatingDAOImpl implements RatingDAO {

   

    @Override
    public void addRating(Rating rating) {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(rating);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateRating(Rating rating) {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(rating);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteRating(Rating rating) {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            Rating managedRating = entityManager.find(Rating.class, rating.getId());
            if (managedRating != null) {
                entityManager.remove(managedRating);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Rating getRatingById(int ratingId) {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            return entityManager.find(Rating.class, ratingId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Rating> getAllRatings() {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Rating r", Rating.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Rating> getRatingsByRecipeId(int recipeId) {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Rating r WHERE r.recipe.id = :recipeId", Rating.class);
            query.setParameter("recipeId", recipeId);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Rating> getRatingsByCustomerId(int customerId) {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Rating r WHERE r.customer.id = :customerId", Rating.class);
            query.setParameter("customerId", customerId);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public double getAverageRatingByRecipeId(int recipeId) {
    	 EntityManager entityManager = null;
         try {
         	entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT AVG(r.ratingValue) FROM Rating r WHERE r.recipe.id = :recipeId");
            query.setParameter("recipeId", recipeId);
            Double averageRating = (Double) query.getSingleResult();
            return averageRating != null ? averageRating : 0;
        } finally {
            entityManager.close();
        }
    }
}

