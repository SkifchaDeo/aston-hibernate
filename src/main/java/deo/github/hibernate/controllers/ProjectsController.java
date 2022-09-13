package deo.github.hibernate.controllers;

import deo.github.hibernate.dto.ProjectDTO;
import deo.github.hibernate.models.Project;
import deo.github.hibernate.services.ProjectService;
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
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectService projectService;

    @Autowired
    public ProjectsController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("projects", projectService.showAllProject());
        return "projects/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.show(id));
        return "projects/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("project") ProjectDTO projectDTO) {
        return "projects/new";
    }

    @PostMapping
    public String create(@ModelAttribute("project") ProjectDTO projectDTO) {
        projectService.saveProject(projectDTO);
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("project", projectService.show(id));
        return "projects/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("project") Project project, @PathVariable("id") int id) {
        projectService.update(id, project);
        return "redirect:/projects";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        projectService.delete(id);
        return "redirect:/projects";
    }
}
