package dao;

import entity.Customer;
import entity.LoggedInUserId;
import exception.SomethingWentWrongException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CustomerDAOImpl implements CustomerDAO {

    /**
     * Registers a new customer in the database.
     * 
     * @param customer The customer to be registered.
     */
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

    /**
     * Retrieves a customer by their unique ID.
     * 
     * @param customerId The ID of the customer to retrieve.
     * @return The customer with the specified ID or null if not found.
     */
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

    /**
     * Retrieves a customer by their unique username.
     * 
     * @param username The username of the customer to retrieve.
     * @return The customer with the specified username or null if not found.
     */
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

    /**
     * Updates the information of an existing customer in the database.
     * 
     * @param customer The customer with updated information.
     */
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

    /**
     * Deletes an existing customer from the database.
     * 
     * @param customer The customer to be deleted.
     */
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

    /**
     * Logs in a customer with the given username and password, setting the
     * logged-in user ID if successful.
     * 
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @throws SomethingWentWrongException If there's an issue during login.
     */
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
