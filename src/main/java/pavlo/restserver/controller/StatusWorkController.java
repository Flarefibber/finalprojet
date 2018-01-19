package pavlo.restserver.controller;

import org.springframework.http.MediaType;
import pavlo.restserver.Service.StatusWorkService;
import pavlo.restserver.model.StatusWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuswork")
public class StatusWorkController {

    @Autowired
    private StatusWorkService statusWorkService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StatusWork> addStatusWork(@RequestBody StatusWork statusWork) {
        statusWorkService.save(statusWork);

        return new ResponseEntity<StatusWork>(statusWork, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> updateStatusWork(@RequestBody StatusWork statusWork) {
        StatusWork existingStatusWork = statusWorkService.findById(statusWork.getId());

        if (existingStatusWork == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            statusWorkService.save(statusWork);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteStatusWork(@PathVariable("id") long id) {
        StatusWork statusWork = statusWorkService.findById(id);

        if (statusWork == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            statusWorkService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<StatusWork>> getAllStatusWork() {
        List<StatusWork> statusWorkList = statusWorkService.findAllStatusWork();

        if (statusWorkList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statusWorkList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StatusWork> getStatusWork(@PathVariable("id") long id) {
        StatusWork statusWork = statusWorkService.findById(id);

        if (statusWork == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusWork, HttpStatus.OK);
    }
}
