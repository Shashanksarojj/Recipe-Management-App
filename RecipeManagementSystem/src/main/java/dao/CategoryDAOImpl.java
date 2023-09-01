package dao;

import java.util.List;

import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class CategoryDAOImpl implements CategoryDAO {

    // Adds a new Category to the database
    @Override
    public void addCategory(Category category) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Updates an existing Category in the database
    @Override
    public void updateCategory(Category category) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Deletes a Category from the database
    @Override
    public void deleteCategory(Category category) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            Category managedCategory = entityManager.find(Category.class, category.getId());
            if (managedCategory != null) {
                entityManager.remove(managedCategory);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    // Retrieves a Category by its ID from the database
    @Override
    public Category getCategoryById(int categoryId) {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            return entityManager.find(Category.class, categoryId);
        } finally {
            entityManager.close();
        }
    }

    // Retrieves all Categories from the database
    @Override
    public List<Category> getAllCategories() {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getEntityManager();
            Query query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
