package deo.github.hibernate.dao;

import deo.github.hibernate.models.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class PositionDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public PositionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Position> showAllPositions() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Position p", Position.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Position show(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Position.class, id);
    }

    @Transactional
    public void save(Position position) {
        Session session = sessionFactory.getCurrentSession();

        session.save(position);
    }

    @Transactional
    public void update(int id, Position updatedPosition) {
        Session session = sessionFactory.getCurrentSession();

        Position positionToBeUpdated = session.get(Position.class, id);

        positionToBeUpdated.setPositionName(updatedPosition.getPositionName());
        positionToBeUpdated.setDepartment(updatedPosition.getDepartment());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Position position = session.get(Position.class, id);

        session.remove(position);

        //for hibernate cash
        position.getEmployeeList().forEach(i -> i.setPosition(null));
    }
}