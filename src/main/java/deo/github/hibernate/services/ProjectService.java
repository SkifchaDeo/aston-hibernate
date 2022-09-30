package deo.github.hibernate.services;

import deo.github.hibernate.dao.ProjectDAO;
import deo.github.hibernate.dto.ProjectDTO;
import deo.github.hibernate.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProjectService {

    private final ProjectDAO projectDAO;

    @Autowired
    public ProjectService(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public List<Project> showAllProject() {
        return projectDAO.showAllProject();
    }

    public Project show(int id) {
        return projectDAO.show(id);
    }

    public void saveProject(ProjectDTO projectDTO) {
        projectDAO.save(projectDTO);
    }

    public void update(int id, Project project) {
        projectDAO.update(id, project);
    }

    public void delete(int id) {
        projectDAO.delete(id);
    }
}
