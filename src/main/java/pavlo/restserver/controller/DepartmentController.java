package pavlo.restserver.controller;

import org.springframework.http.MediaType;
import pavlo.restserver.Service.DepartmentService;
import pavlo.restserver.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        departmentService.save(department);

        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        Department existingDepartment = departmentService.findById(department.getId());

        if (existingDepartment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            departmentService.save(department);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") long id) {
        Department department = departmentService.findById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            departmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Department>> getAllDepartment() {
        List<Department> departmentList = departmentService.findAllDepartment();

        if (departmentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> getDepartment(@PathVariable("id") long id) {
        Department department = departmentService.findById(id);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Department>> getDepartmentByName(@PathVariable("name") String name) {
        List<Department> departmentList = departmentService.findByName(name);

        if (departmentList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }
}
