package dao;

import java.util.List;

import entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ReviewDAOImpl implements ReviewDAO {

    // Adds a new Review to the database
    @Override
    public void addReview(Review review) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(review);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Updates an existing Review in the database
    @Override
    public void updateReview(Review review) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(review);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Deletes a Review from the database
    @Override
    public void deleteReview(Review review) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            Review managedReview = entityManager.find(Review.class, review.getId());
            if (managedReview != null) {
                entityManager.remove(managedReview);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Retrieves a Review by its ID from the database
    @Override
    public Review getReviewById(int reviewId) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            return entityManager.find(Review.class, reviewId);
        } finally {
            entityManager.close();
        }
    }

    // Retrieves all Reviews from the database
    @Override
    public List<Review> getAllReviews() {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Review r", Review.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    // Retrieves Reviews by a specific recipe ID
    @Override
    public List<Review> getReviewsByRecipeId(int recipeId) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Review r WHERE r.recipe.id = :recipeId", Review.class);
            query.setParameter("recipeId", recipeId);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    // Retrieves Reviews by a specific customer ID
    @Override
    public List<Review> getReviewsByCustomerId(int customerId) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT r FROM Review r WHERE r.customer.id = :customerId", Review.class);
            query.setParameter("customerId", customerId);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
