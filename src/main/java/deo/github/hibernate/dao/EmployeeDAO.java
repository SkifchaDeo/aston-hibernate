package deo.github.hibernate.dao;

import deo.github.hibernate.dto.EmployeeDTO;
import deo.github.hibernate.models.Employee;
import deo.github.hibernate.models.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EmployeeDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Employee> showAllEmployee() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Employee show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Employee.class, id);
    }

    @Transactional
    public void save(EmployeeDTO employeeDto) {
        Session session = sessionFactory.getCurrentSession();

        Position position = session.get(Position.class, employeeDto.getPositionId());
        Employee employee = new Employee(employeeDto.getLastName(), employeeDto.getAge(), position);

        position.getEmployeeList().add(employee);

        session.save(employee);
    }

    @Transactional
    public void update(int id, Employee employee) {
        Session session = sessionFactory.getCurrentSession();

        Employee employeeToBeUpdated = session.get(Employee.class, id);

        employeeToBeUpdated.setLastName(employee.getLastName());
        employeeToBeUpdated.setAge(employee.getAge());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);
        session.remove(employee);

        //for hibernate cash
        employee.getPosition().getEmployeeList().remove(employee);
    }
}