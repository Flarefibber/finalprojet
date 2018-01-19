package pavlo.restserver.service;


import pavlo.restserver.model.Event;
import pavlo.restserver.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event findById(Long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> findByType(String type) {
        return eventRepository.findByType(type);
    }

    @Override
    public List<Event> findAllEvent() {
        List<Event> list = eventRepository.findAll();
        if (list != null) {
            return list;
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        Event saveEvent = eventRepository.save(event);
        return saveEvent;
    }

    @Override
    public void update(Event event) {
        Event event1 = eventRepository.findOne(event.getId());
        if (event1 != null) {
            eventRepository.delete(event.getId());
        }
        eventRepository.save(event);
    }

    @Override
    public void delete(Long id) {
        Event event1 = eventRepository.findOne(id);
        if (event1 != null) {
            eventRepository.delete(id);
        }
    }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }
}
