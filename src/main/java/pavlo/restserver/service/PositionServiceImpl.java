package pavlo.restserver.service;


import pavlo.restserver.model.Position;
import pavlo.restserver.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Position findById(Long id) {
        Position position1 = positionRepository.findOne(id);
        if (position1 != null) {
            return position1;
        }
        return null;
    }

    @Override
    public List<Position> findAllPosition() {
        List<Position> list = positionRepository.findAll();
        if (list != null) {
            return list;
        }
        return null;
    }

    @Override
    public List<Position> findByName(String name) {
        List<Position> list = positionRepository.findByName(name);
        if (list != null) {
            return list;
        }
        return null;
    }

    @Override
    public Position save(Position position) {
        Position savePosition = positionRepository.save(position);
        return savePosition;
    }

    @Override
    public void update(Position position) {

        Position position1 = positionRepository.findOne(position.getId());
        if (position1 != null) {
            positionRepository.delete(position1);
            positionRepository.save(position);
        }

    }

    @Override
    public void delete(Long id) {
        Position position1 = positionRepository.findOne(id);
        if (position1 != null) {
            positionRepository.delete(position1);
        }

    }

    @Override
    public void deleteAll() {
        positionRepository.deleteAll();
    }
}
