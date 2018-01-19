package pavlo.restserver.controller;

import org.springframework.http.MediaType;
import pavlo.restserver.service.DateEventService;
import pavlo.restserver.model.DateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dateevent")
public class DateEventController {

    @Autowired
    private DateEventService dateEventService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DateEvent> addDateEvent(@RequestBody DateEvent dateEvent) {
        dateEventService.save(dateEvent);

        return new ResponseEntity<DateEvent>(dateEvent, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> updateDateEvent(@RequestBody DateEvent dateEvent) {
        DateEvent existingDateEvent = dateEventService.findById(dateEvent.getId());

        if (existingDateEvent == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            dateEventService.save(dateEvent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteDateEvent(@PathVariable("id") long id) {
        DateEvent dateEvent = dateEventService.findById(id);

        if (dateEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            dateEventService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<DateEvent>> getAllDateEvent() {
        List<DateEvent> dateEventList = dateEventService.findAllEvents();

        if (dateEventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dateEventList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DateEvent> getDateEvent(@PathVariable("id") long id) {
        DateEvent dateEvent = dateEventService.findById(id);

        if (dateEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateEvent, HttpStatus.OK);
    }

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DateEvent> getDateEventByFirstName(@PathVariable("date") String date) {
        DateEvent dateEventList = dateEventService.findByDate(date);

        if (dateEventList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateEventList, HttpStatus.OK);
    }
}
