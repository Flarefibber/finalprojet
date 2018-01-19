package pavlo.restserver.service;


import pavlo.restserver.model.Event;

import java.util.List;

public interface EventService {

    Event findById(Long id);

    List<Event> findByType(String type);

    List<Event> findAllEvent();

    Event save(Event event);

    void update(Event event);

    void delete(Long id);

    void deleteAll();
}
