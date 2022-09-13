package deo.github.hibernate.dao;

import deo.github.hibernate.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CustomerDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Customer> showAllCustomers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Customer show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Transactional
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        session.save(customer);
    }

    @Transactional
    public void update(int id, Customer updatedCustomer) {
        Session session = sessionFactory.getCurrentSession();

        Customer customerToBeUpdated = session.get(Customer.class, id);

        customerToBeUpdated.setName(updatedCustomer.getName());
        customerToBeUpdated.setEmail(updatedCustomer.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, id);

        session.remove(customer);

        //for hibernate cash
        customer.getProjectList().forEach(i -> i.setCustomer(null));
    }
}