package service;

import java.util.List;

import dao.ReviewDAO;
import entity.Review;
import exception.ServiceException;

public class ReviewServiceImpl implements ReviewService {
    private ReviewDAO reviewDAO;

    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    public void addReview(Review review) throws ServiceException {
        try {
            reviewDAO.addReview(review);
        } catch (Exception e) {
            throw new ServiceException("Failed to add review", e);
        }
    }

    @Override
    public void updateReview(Review review) throws ServiceException {
        try {
            reviewDAO.updateReview(review);
        } catch (Exception e) {
            throw new ServiceException("Failed to update review", e);
        }
    }

    @Override
    public void deleteReview(Review review) throws ServiceException {
        try {
            reviewDAO.deleteReview(review);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete review", e);
        }
    }

    @Override
    public Review getReviewById(int reviewId) throws ServiceException {
        try {
            return reviewDAO.getReviewById(reviewId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get review by ID", e);
        }
    }

    @Override
    public List<Review> getAllReviews() throws ServiceException {
        try {
            return reviewDAO.getAllReviews();
        } catch (Exception e) {
            throw new ServiceException("Failed to get all reviews", e);
        }
    }

    @Override
    public List<Review> getReviewsByRecipeId(int recipeId) throws ServiceException {
        try {
            return reviewDAO.getReviewsByRecipeId(recipeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get reviews by recipe ID", e);
        }
    }

    @Override
    public List<Review> getReviewsByCustomerId(int customerId) throws ServiceException {
        try {
            return reviewDAO.getReviewsByCustomerId(customerId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get reviews by customer ID", e);
        }
    }
}