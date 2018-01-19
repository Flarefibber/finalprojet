package pavlo.restserver.controller;

import org.springframework.http.MediaType;
import pavlo.restserver.Service.EventService;
import pavlo.restserver.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        eventService.save(event);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> updateEvent(@RequestBody Event event) {
        Event existingEvent = eventService.findById(event.getId());

        if (existingEvent == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            eventService.save(event);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") long id) {
        Event event = eventService.findById(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            eventService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Event>> getAllEvent() {
        List<Event> eventList = eventService.findAllEvent();

        if (eventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable("id") long id) {
        Event event = eventService.findById(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Event>> getEventByType(@PathVariable("type") String type) {
        List<Event> eventList = eventService.findByType(type);

        if (eventList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }
}
