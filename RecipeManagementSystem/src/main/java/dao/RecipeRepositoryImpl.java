package dao;

import java.util.List;

import entity.Ingredient;
import entity.Recipe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class RecipeRepositoryImpl implements RecipeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Recipe findById(int recipeId) {
        return entityManager.find(Recipe.class, recipeId);
    }

    @Override
    public void save(Recipe recipe) {
        entityManager.persist(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        entityManager.merge(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        entityManager.remove(recipe);
    }

    // Additional methods implementation

//    @Override
//    public List<Recipe> findAll() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Recipe> query = criteriaBuilder.createQuery(Recipe.class);
//        Root<Recipe> root = query.from(Recipe.class);
//        query.select(root);
//        return entityManager.createQuery(query).getResultList();
//    }

//    @Override
//    public List<Recipe> searchByName(String keyword) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Recipe> query = criteriaBuilder.createQuery(Recipe.class);
//        Root<Recipe> root = query.from(Recipe.class);
//        query.select(root)
//                .where(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"));
//        return entityManager.createQuery(query).getResultList();
//    }
//
//    @Override
//    public List<Recipe> searchByIngredient(String ingredientName) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Recipe> query = criteriaBuilder.createQuery(Recipe.class);
//        Root<Recipe> root = query.from(Recipe.class);
//        Join<Recipe, Ingredient> ingredientJoin = root.join("ingredients");
//        query.select(root)
//                .where(criteriaBuilder.equal(criteriaBuilder.lower(ingredientJoin.get("name")), ingredientName.toLowerCase()));
//        return entityManager.createQuery(query).getResultList();
//    }
//
//    @Override
//    public List<Recipe> getTopRatedRecipes(int limit) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Recipe> query = criteriaBuilder.createQuery(Recipe.class);
//        Root<Recipe> root = query.from(Recipe.class);
//        query.select(root)
//                .orderBy(criteriaBuilder.desc(root.get("likes").size()));
//        return entityManager.createQuery(query).setMaxResults(limit).getResultList();
//    }
//
//    @Override
//    public List<Recipe> getLatestRecipes(int limit) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Recipe> query = criteriaBuilder.createQuery(Recipe.class);
//        Root<Recipe> root = query.from(Recipe.class);
//        query.select(root)
//                .orderBy(criteriaBuilder.desc(root.get("id")));
//        return entityManager.createQuery(query).setMaxResults(limit).getResultList();
//    }
}
