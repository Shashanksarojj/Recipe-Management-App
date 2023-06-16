package service;

import java.util.List;
import entity.Review;
import exception.ServiceException;

public interface ReviewService {
	
	 void addReview(Review review) throws ServiceException;
	    void updateReview(Review review) throws ServiceException;
	    void deleteReview(Review review) throws ServiceException;
	    Review getReviewById(int reviewId) throws ServiceException;
	    List<Review> getAllReviews() throws ServiceException;
	    List<Review> getReviewsByRecipeId(int recipeId) throws ServiceException;
	    List<Review> getReviewsByCustomerId(int customerId) throws ServiceException;
}
