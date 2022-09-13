package deo.github.hibernate.controllers;

import deo.github.hibernate.models.Customer;
import deo.github.hibernate.services.CustomerService;
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
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("customers", customerService.findAllCustomers());
        return "customers/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.show(id));
        return "customers/show";
    }

    @GetMapping("/new")
    public String newCustomer(@ModelAttribute("customer") Customer customer) {
        return "customers/new";
    }

    @PostMapping
    public String create(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("customer", customerService.show(id));
        return "customers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("customer") Customer customer, @PathVariable("id") int id) {
        customerService.update(id, customer);
        return "redirect:/customers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}
