package deo.github.hibernate.controllers;

import deo.github.hibernate.dto.EmployeeDTO;
import deo.github.hibernate.models.Employee;
import deo.github.hibernate.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("employees", employeeService.showAllEmployee());
        return "employees/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", employeeService.show(id));
        return "employees/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
        return "employees/new";
    }

    @PostMapping
    public String create(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
        return "redirect:/employees";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeService.show(id));
        return "employees/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("employee") Employee employee, @PathVariable("id") int id) {
        employeeService.update(id, employee);
        return "redirect:/employees";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

}
