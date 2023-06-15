package dao;

import javax.management.Query;

import entity.Customer;
import entity.LoggedInUserId;
import exception.SomethingWentWrongException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;


public class CustomerDAOImpl implements CustomerDAO {

    private EntityManagerFactory entityManagerFactory;

    public CustomerDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("recipe-management-system");
    }

    @Override
    public void registerCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Customer.class, customerId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
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

    public void close() {
        entityManagerFactory.close();
    }
}
