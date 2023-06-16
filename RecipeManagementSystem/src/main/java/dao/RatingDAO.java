package dao;

import java.util.List;

import entity.Rating;

public interface RatingDAO {

    void addRating(Rating rating);

    void updateRating(Rating rating);

    void deleteRating(Rating rating);

    Rating getRatingById(int ratingId);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByRecipeId(int recipeId);

    List<Rating> getRatingsByCustomerId(int customerId);

    double getAverageRatingByRecipeId(int recipeId);
}
