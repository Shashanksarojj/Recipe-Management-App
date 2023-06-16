package dao;

import entity.Like;
import exception.NoRecordFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class LikeDAOImpl implements LikeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Like getLikeById(long likeId) throws NoRecordFoundException {
        Like like = entityManager.find(Like.class, likeId);
        if (like == null) {
            throw new NoRecordFoundException("Like not found with ID: " + likeId);
        }
        return like;
    }

    @Override
    public void addLike(Like like) {
        entityManager.persist(like);
    }

    @Override
    public void updateLike(Like like) throws NoRecordFoundException {
        Like existingLike = getLikeById(like.getLikeId());
        existingLike.setRecipe(like.getRecipe());
        existingLike.setCustomer(like.getCustomer());
        existingLike.setLiked(like.isLiked());
        entityManager.merge(existingLike);
    }

    @Override
    public void deleteLike(long likeId) throws NoRecordFoundException {
        Like like = getLikeById(likeId);
        entityManager.remove(like);
    }

    @Override
    public boolean isLikeExists(long recipeId, long customerId) {
        String query = "SELECT COUNT(*) FROM Like l WHERE l.recipe.recipeId = :recipeId AND l.customer.customerId = :customerId";
        Long count = entityManager.createQuery(query, Long.class)
                .setParameter("recipeId", recipeId)
                .setParameter("customerId", customerId)
                .getSingleResult();
        return count > 0;
    }
}
