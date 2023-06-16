package service;

import dao.LikeDAO;
import entity.Like;
import exception.NoRecordFoundException;
import exception.ServiceException;


public class LikeServiceImpl implements LikeService {

    private final LikeDAO likeDAO;

    public LikeServiceImpl(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    @Override
    public Like getLikeById(long likeId) throws NoRecordFoundException, ServiceException {
        try {
            return likeDAO.getLikeById(likeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve like", e);
        }
    }

    @Override
    public void addLike(Like like) throws ServiceException {
        try {
            likeDAO.addLike(like);
        } catch (Exception e) {
            throw new ServiceException("Failed to add like", e);
        }
    }

    @Override
    public void updateLike(Like like) throws NoRecordFoundException, ServiceException {
        try {
            likeDAO.updateLike(like);
        } catch (Exception e) {
            throw new ServiceException("Failed to update like", e);
        }
    }

    @Override
    public void deleteLike(long likeId) throws NoRecordFoundException, ServiceException {
        try {
            likeDAO.deleteLike(likeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete like", e);
        }
    }

    @Override
    public boolean isLikeExists(long recipeId, long customerId) throws ServiceException {
        try {
            return likeDAO.isLikeExists(recipeId, customerId);
        } catch (Exception e) {
            throw new ServiceException("Failed to check if like exists", e);
        }
    }
}