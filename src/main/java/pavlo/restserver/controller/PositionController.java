package pavlo.restserver.controller;

import org.springframework.http.MediaType;
import pavlo.restserver.service.PositionService;
import pavlo.restserver.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Position> addPosition(@RequestBody Position position) {
        positionService.save(position);

        return new ResponseEntity<>(position, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> updatePosition(@RequestBody Position position) {
        Position existingPosition = positionService.findById(position.getId());

        if (existingPosition == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            positionService.save(position);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deletePosition(@PathVariable("id") long id) {
        Position position = positionService.findById(id);

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            positionService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Position>> getAllPosition() {
        List<Position> positionList = positionService.findAllPosition();

        if (positionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Position> getPosition(@PathVariable("id") long id) {
        Position position = positionService.findById(id);

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Position>> getPositionByName(@PathVariable("name") String name) {
        List<Position> positionList = positionService.findByName(name);

        if (positionList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }
}
