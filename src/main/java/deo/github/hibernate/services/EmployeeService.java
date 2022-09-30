package deo.github.hibernate.services;

import deo.github.hibernate.dao.EmployeeDAO;
import deo.github.hibernate.dto.EmployeeDTO;
import deo.github.hibernate.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> showAllEmployee() {
        return employeeDAO.showAllEmployee();
    }

    public Employee show(int id) {
        return employeeDAO.show(id);
    }

    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeDAO.save(employeeDTO);
    }

    public void update(int id, Employee employee) {
        employeeDAO.update(id, employee);
    }

    public void delete(int id) {
        employeeDAO.delete(id);
    }
}
