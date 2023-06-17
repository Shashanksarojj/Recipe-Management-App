package service;

import java.util.List;

import dao.EMUtils;
import entity.Category;
import exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public void addCategory(Category category) throws ServiceException {
       
    	 EntityTransaction transaction = null;
         EntityManager em = null;
         try {
//         	em = EMUtils.getEntityManager();
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
//            emf.close();
        }
    }

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
//            emf.close();
        }
    }

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
//            emf.close();
        }
    }

    @Override
    public Category getCategoryById(int categoryId) throws ServiceException {
//    	 EntityTransaction transaction = null;
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
//            emf.close();
        }
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
//    	 EntityTransaction transaction = null;
         EntityManager em = null;
         try {
         	em = EMUtils.getEntityManager();
            Query query = em.createQuery("SELECT c FROM Category c");
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve categories.", e);
        } finally {
            em.close();
//            emf.close();
        }
    }
}