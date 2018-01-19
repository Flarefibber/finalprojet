package pavlo.restserver.service;


import pavlo.restserver.model.StatusWork;
import pavlo.restserver.repository.StatusWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusWorkServiceImpl implements StatusWorkService {

    @Autowired
    private StatusWorkRepository statusWorkRepository;

    @Override
    public StatusWork findByStatusWork(String status) {
        StatusWork statusWork = statusWorkRepository.findByName(status);
        if (statusWork != null) {
            return statusWork;
        }
        return null;
    }

    @Override
    public StatusWork findById(Long id) {
        StatusWork statusWork = statusWorkRepository.findOne(id);
        if (statusWork != null) {
            return statusWork;
        }
        return null;
    }

    @Override
    public List<StatusWork> findAllStatusWork() {
        return statusWorkRepository.findAll();
    }

    @Override
    public StatusWork save(StatusWork statusWork) {
        StatusWork saveStatusWork = statusWorkRepository.save(statusWork);
        return saveStatusWork;
    }

    @Override
    public void update(StatusWork statusWork) {
        statusWorkRepository.save(statusWork);
    }

    @Override
    public void delete(Long id) {
        StatusWork statusWork = statusWorkRepository.findOne(id);
        if (statusWork != null) {
            statusWorkRepository.delete(statusWork);
        }
    }

    @Override
    public void deleteAll() {
        statusWorkRepository.deleteAll();
    }

}
