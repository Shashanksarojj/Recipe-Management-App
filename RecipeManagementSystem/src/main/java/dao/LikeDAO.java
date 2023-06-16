package dao;

import entity.Like;
import exception.NoRecordFoundException;

public interface LikeDAO {

    Like getLikeById(long likeId) throws NoRecordFoundException;

    void addLike(Like like);

    void updateLike(Like like) throws NoRecordFoundException;

    void deleteLike(long likeId) throws NoRecordFoundException;

    boolean isLikeExists(long recipeId, long customerId);
}
