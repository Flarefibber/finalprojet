package pavlo.restserver.controller;

import org.springframework.http.MediaType;
import pavlo.restserver.service.DateStatusService;
import pavlo.restserver.service.EmployeeService;
import pavlo.restserver.model.DateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datestatus")
public class DateStatusController {
    @Autowired
    private DateStatusService dateStatusService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DateStatus> addDateStatus(@RequestBody DateStatus dateStatus) {
        dateStatusService.save(dateStatus);

        return new ResponseEntity<>(dateStatus, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> updateDatestatus(@RequestBody DateStatus dateStatus) {
        DateStatus existingDateStatus = dateStatusService.findById(dateStatus.getId());

        if (existingDateStatus == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            dateStatusService.save(dateStatus);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteDateStatus(@PathVariable("id") long id) {
        DateStatus dateStatus = dateStatusService.findById(id);

        if (dateStatus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            dateStatusService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<DateStatus>> getAllDateStatus() {
        List<DateStatus> dateStatusList = dateStatusService.findAllSatuses();

        if (dateStatusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DateStatus> getDateStatus(@PathVariable("id") long id) {
        DateStatus dateStatus = dateStatusService.findById(id);

        if (dateStatus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<DateStatus>> getDateEventByDate(@PathVariable("date") String date) {
        List<DateStatus> dateStatusList = dateStatusService.findByDate(date);

        if (dateStatusList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<DateStatus>> getDateEventByEmployee(@PathVariable("id") Long id) {
        List<DateStatus> dateStatusList = dateStatusService.findByEmployee(employeeService.getById(id));

        if (dateStatusList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }

    @RequestMapping(value = "/employeedate/{id}/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<DateStatus>> getDateEventByEmployee(@PathVariable("id") Long id, @PathVariable("date") String date) {
        List<DateStatus> dateStatusList = dateStatusService.findByEmployeeAndDate(employeeService.getById(id), date);

        if (dateStatusList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dateStatusList, HttpStatus.OK);
    }
}
