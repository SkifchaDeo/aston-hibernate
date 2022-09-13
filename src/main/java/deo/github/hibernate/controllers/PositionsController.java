package deo.github.hibernate.controllers;

import deo.github.hibernate.models.Position;
import deo.github.hibernate.services.PositionService;
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
@RequestMapping("/positions")
public class PositionsController {

    private final PositionService positionService;

    @Autowired
    public PositionsController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("positions", positionService.findAllPositions());
        return "positions/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("position", positionService.show(id));
        return "positions/show";
    }

    @GetMapping("/new")
    public String newPosition(@ModelAttribute("position") Position position) {
        return "positions/new";
    }

    @PostMapping
    public String create(@ModelAttribute("position") Position position) {
        positionService.savePosition(position);
        return "redirect:/positions";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("position", positionService.show(id));
        return "positions/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("position") Position position, @PathVariable("id") int id) {
        positionService.update(id, position);
        return "redirect:/positions";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        positionService.delete(id);
        return "redirect:/positions";
    }
}
