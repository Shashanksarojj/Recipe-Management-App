package dao;

import entity.Customer;
import entity.LoggedInUserId;
import exception.SomethingWentWrongException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


public class CustomerDAOImpl implements CustomerDAO {

  

    @Override
    public void registerCustomer(Customer customer) {
    	 EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            return entityManager.find(Customer.class, customerId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            TypedQuery<Customer> query = entityManager.createQuery("FROM Customer WHERE username = :username", Customer.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteCustomer(Customer customer) {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void login(String username, String password) throws SomethingWentWrongException {
        EntityManager entityManager = null;
        try {
        	entityManager = EMUtils.getEntityManager();
        	TypedQuery<Customer> query = entityManager.createQuery("FROM Customer WHERE username = :username", Customer.class);
            query.setParameter("username", username);
            Customer customer = query.getSingleResult();

            if (customer == null || !customer.getPassword().equals(password)) {
                throw new SomethingWentWrongException("Invalid username or password");
            }

            // Perform necessary validation and authentication checks
            // Set the logged-in user ID or any other necessary information
            LoggedInUserId.setLoggedInUserId(customer.getId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new SomethingWentWrongException("Something went wrong during login");
        } finally {
            entityManager.close();
        }
    }

}
