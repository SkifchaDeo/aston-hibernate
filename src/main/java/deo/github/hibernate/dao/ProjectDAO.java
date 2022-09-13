package deo.github.hibernate.dao;

import deo.github.hibernate.dto.ProjectDTO;
import deo.github.hibernate.models.Customer;
import deo.github.hibernate.models.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;




import java.util.List;

@Component
public class ProjectDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ProjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Project> showAllProject() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Project show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Project.class, id);
    }

    @Transactional
    public void save(ProjectDTO projectDto) {
        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, projectDto.getCustomerId());
        Project project = new Project(projectDto.getProjectName(), projectDto.getCost(), customer);

        customer.getProjectList().add(project);

        session.save(project);
    }

    @Transactional
    public void update(int id, Project project) {
        Session session = sessionFactory.getCurrentSession();

        Project projectToBeUpdated = session.get(Project.class, id);

        projectToBeUpdated.setProjectName(project.getProjectName());
        projectToBeUpdated.setCost(project.getCost());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Project project = session.get(Project.class, id);

        session.remove(project);

        //for hibernate cash
        project.getCustomer().getProjectList().remove(project);
    }
}