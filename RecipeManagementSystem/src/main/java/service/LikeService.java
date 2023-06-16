package service;
import entity.Like;
import exception.NoRecordFoundException;
import exception.ServiceException;

public interface LikeService {

    Like getLikeById(long likeId) throws NoRecordFoundException, ServiceException;

    void addLike(Like like) throws ServiceException;

    void updateLike(Like like) throws NoRecordFoundException, ServiceException;

    void deleteLike(long likeId) throws NoRecordFoundException, ServiceException;

    boolean isLikeExists(long recipeId, long customerId) throws ServiceException;
}

