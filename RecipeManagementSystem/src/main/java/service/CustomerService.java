package service;



import entity.Customer;
import exception.NoRecordFoundException;
import exception.ServiceException;
import exception.SomethingWentWrongException;

public interface CustomerService {

    void registerCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    Customer getCustomerByUsername(String username);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    void login(String username, String password) throws SomethingWentWrongException;
}
