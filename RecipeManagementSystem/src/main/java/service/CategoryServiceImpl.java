package service;

import java.util.List;

import dao.EMUtils;
import entity.Category;
import exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class CategoryServiceImpl implements CategoryService {

    // Adds a new Category to the database
    @Override
    public void addCategory(Category category) throws ServiceException {
        EntityTransaction transaction = null;
        EntityManager em = null;
        try {
            em = EMUtils.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ServiceException("Failed to add category.", e);
        } finally {
            em.close();
        }
    }

    // Updates an existing Category in the database
    @Override
    public void updateCategory(Category category) throws ServiceException {
        EntityTransaction transaction = null;
        EntityManager em = null;
        try {
            em = EMUtils.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ServiceException("Failed to update category.", e);
        } finally {
            em.close();
        }
    }

    // Deletes a Category from the database by its ID
    @Override
    public void deleteCategory(int categoryId) throws ServiceException {
        EntityTransaction transaction = null;
        EntityManager em = null;
        try {
            em = EMUtils.getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            Category category = em.find(Category.class, categoryId);
            if (category != null) {
                em.remove(category);
                transaction.commit();
            } else {
                throw new ServiceException("Category not found.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ServiceException("Failed to delete category.", e);
        } finally {
            em.close();
        }
    }

    // Retrieves a Category by its ID from the database
    @Override
    public Category getCategoryById(int categoryId) throws ServiceException {
        EntityManager em = null;
        try {
            em = EMUtils.getEntityManager();
            Category category = em.find(Category.class, categoryId);
            if (category != null) {
                return category;
            } else {
                throw new ServiceException("Category not found.");
            }
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve category.", e);
        } finally {
            em.close();
        }
    }

    // Retrieves all Categories from the database
    @Override
    public List<Category> getAllCategories() throws ServiceException {
        EntityManager em = null;
        try {
            em = EMUtils.getEntityManager();
            Query query = em.createQuery("SELECT c FROM Category c");
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve categories.", e);
        } finally {
            em.close();
        }
    }
}
