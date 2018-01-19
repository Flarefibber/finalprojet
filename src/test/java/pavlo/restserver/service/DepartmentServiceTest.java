package pavlo.restserver.service;

import pavlo.restserver.Service.DepartmentService;
import pavlo.restserver.model.Department;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Before
    public void beforeTest() {
        departmentService.deleteAll();
    }

    @Test
    public void saveTest() {
        Department department = departmentService.save(new Department("test"));
        Department id = departmentService.findById(department.getId());

        assertEquals(department, id);
    }
}
