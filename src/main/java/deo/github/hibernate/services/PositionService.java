package deo.github.hibernate.services;

import deo.github.hibernate.dao.PositionDAO;
import deo.github.hibernate.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PositionService {

    private final PositionDAO positionDAO;

    @Autowired
    public PositionService(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    public List<Position> findAllPositions() {
        return positionDAO.showAllPositions();
    }

    public Position show(int id) {
        return positionDAO.show(id);
    }

    public void savePosition(Position position) {
        positionDAO.save(position);
    }

    public void update(int id, Position updatedPosition) {
        positionDAO.update(id, updatedPosition);
    }

    public void delete(int id) {
        positionDAO.delete(id);
    }
}
