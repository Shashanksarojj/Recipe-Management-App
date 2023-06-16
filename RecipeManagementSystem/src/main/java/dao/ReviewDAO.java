package dao;

import java.util.List;

import entity.Review;

public interface ReviewDAO {


    void addReview(Review review);

    void updateReview(Review review);

    void deleteReview(Review review);

    Review getReviewById(int reviewId);

    List<Review> getAllReviews();

    List<Review> getReviewsByRecipeId(int recipeId);

    List<Review> getReviewsByCustomerId(int customerId);
}
