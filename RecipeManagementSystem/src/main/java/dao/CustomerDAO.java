package dao;

import entity.Customer;
import exception.SomethingWentWrongException;

public interface CustomerDAO {

    void registerCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    Customer getCustomerByUsername(String username);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    void login(String username, String password) throws SomethingWentWrongException;
}
