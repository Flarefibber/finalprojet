package pavlo.restserver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pavlo.restserver.service.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventServiceTest.class,
        DateEventServiceTest.class,
        DateStatusServiceTest.class,
        DepartmentServiceTest.class,
        PositionServiceTest.class,
        StatusWorkServiceTest.class,
        ReportServiceTest.class,
        EmployeeServiceTest.class
})
public class ServiceTestSuite {
}
