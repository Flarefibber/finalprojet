package pavlo.restserver.service;

import pavlo.restserver.Service.DepartmentService;
import pavlo.restserver.Service.EmployeeService;
import pavlo.restserver.Service.PositionService;
import pavlo.restserver.model.Department;
import pavlo.restserver.model.Employee;
import pavlo.restserver.model.Position;
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
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Before
    public void beforeTest() {
        employeeService.deleteAll();
    }

    @Test
    public void saveTest() {
        Department department = departmentService.save(new Department("test"));
        Position position = positionService.save(new Position("test", 1f));
        Employee employee = new Employee(1L, "test", "test", "test",
                department, position, 1f, 1f, 1f, 0f);

        Employee test = employeeService.save(employee);
        assertEquals(employee, test);
    }
}
