package service;

import dao.CustomerDAO;
import entity.Customer;
import exception.NoRecordFoundException;
import exception.ServiceException;
import exception.SomethingWentWrongException;


public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void registerCustomer(Customer customer) {
        customerDAO.registerCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerDAO.getCustomerByUsername(username);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerDAO.deleteCustomer(customer);
    }

    @Override
    public void login(String username, String password) throws SomethingWentWrongException {
        customerDAO.login(username, password);
    }
}
