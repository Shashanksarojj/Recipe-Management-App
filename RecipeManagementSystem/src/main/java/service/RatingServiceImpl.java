package service;


import java.util.List;

import dao.RatingDAO;
import entity.Rating;
import exception.ServiceException;


public class RatingServiceImpl implements RatingService {
    private RatingDAO ratingDAO;

    public RatingServiceImpl(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    @Override
    public void addRating(Rating rating) throws ServiceException {
        try {
            ratingDAO.addRating(rating);
        } catch (Exception e) {
            throw new ServiceException("Failed to add rating", e);
        }
    }

    @Override
    public void updateRating(Rating rating) throws ServiceException {
        try {
            ratingDAO.updateRating(rating);
        } catch (Exception e) {
            throw new ServiceException("Failed to update rating", e);
        }
    }

    @Override
    public void deleteRating(Rating rating) throws ServiceException {
        try {
            ratingDAO.deleteRating(rating);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete rating", e);
        }
    }

    @Override
    public Rating getRatingById(int ratingId) throws ServiceException {
        try {
            return ratingDAO.getRatingById(ratingId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get rating by ID", e);
        }
    }

    @Override
    public List<Rating> getAllRatings() throws ServiceException {
        try {
            return ratingDAO.getAllRatings();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all ratings", e);
        }
    }

    @Override
    public List<Rating> getRatingsByRecipeId(int recipeId) throws ServiceException {
        try {
            return ratingDAO.getRatingsByRecipeId(recipeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get ratings by recipe ID", e);
        }
    }

    @Override
    public List<Rating> getRatingsByCustomerId(int customerId) throws ServiceException {
        try {
            return ratingDAO.getRatingsByCustomerId(customerId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get ratings by customer ID", e);
        }
    }

    @Override
    public double getAverageRatingByRecipeId(int recipeId) throws ServiceException {
        try {
            return ratingDAO.getAverageRatingByRecipeId(recipeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get average rating by recipe ID", e);
        }
    }
}
