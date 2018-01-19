package pavlo.restserver.service;

import pavlo.restserver.Service.DateStatusService;
import pavlo.restserver.Service.DepartmentService;
import pavlo.restserver.Service.EmployeeService;
import pavlo.restserver.Service.StatusWorkService;
import pavlo.restserver.Service.PositionService;
import pavlo.restserver.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class DateStatusServiceTest {

    @Autowired
    private DateStatusService dateStatusService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StatusWorkService statusWorkService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Before
    public void beforeTest() {
        dateStatusService.deleteAll();
    }

    @Test
    public void saveTest() {
        Department department = departmentService.save(new Department("test"));
        Position position = positionService.save(new Position("test", 1f));

        Employee employee = employeeService.save(new Employee(1L, "test", "test", "test",
                department, position, 1f, 1f, 1f, 0f));
        StatusWork statusWork = statusWorkService.save(new StatusWork("work"));

        DateStatus dateStatus = new DateStatus(employee, "10 01 2018", statusWork);
        dateStatusService.save(dateStatus);
        DateStatus found = dateStatusService.findById(dateStatus.getId());
        assertEquals(dateStatus.getDate(), found.getDate());
    }

}
