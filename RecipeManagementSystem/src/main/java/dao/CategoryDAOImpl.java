package dao;

import java.util.List;

import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CategoryDAOImpl implements CategoryDAO {

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
