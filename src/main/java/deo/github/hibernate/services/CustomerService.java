package deo.github.hibernate.services;

import deo.github.hibernate.dao.CustomerDAO;
import deo.github.hibernate.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> findAllCustomers() {
        return customerDAO.showAllCustomers();
    }

    public Customer show(int id) {
        return customerDAO.show(id);
    }

    public void saveCustomer(Customer customer) {
        customerDAO.save(customer);
    }

    public void update(int id, Customer updatedCustomer) {
        customerDAO.update(id, updatedCustomer);
    }

    public void delete(int id) {
        customerDAO.delete(id);
    }
}
