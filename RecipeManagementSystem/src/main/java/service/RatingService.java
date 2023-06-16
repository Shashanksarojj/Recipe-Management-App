package service;



import java.util.List;

import entity.Rating;
import exception.ServiceException;

public interface RatingService {
	   void addRating(Rating rating) throws ServiceException;
	    void updateRating(Rating rating) throws ServiceException;
	    void deleteRating(Rating rating) throws ServiceException;
	    Rating getRatingById(int ratingId) throws ServiceException;
	    List<Rating> getAllRatings() throws ServiceException;
	    List<Rating> getRatingsByRecipeId(int recipeId) throws ServiceException;
	    List<Rating> getRatingsByCustomerId(int customerId) throws ServiceException;
	    double getAverageRatingByRecipeId(int recipeId) throws ServiceException;
}
