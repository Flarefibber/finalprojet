package pavlo.restserver.service;

import pavlo.restserver.model.Report;
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
public class ReportServiceTest {

    @Autowired
    private WorkReportService workReportService;

    @Before
    public void beforeTest() {
        workReportService.deleteAll();
    }

    @Test
    public void saveTest() {
        Report report = new Report(1L, "01 01 2018", "10 01 2018");
        report.setCountDay(2);
        report.setSalaryPeriod(2000f);
        workReportService.add(report);

        List<Report> found = workReportService.findReportByEmployeeAndBeginPeriodAndEndPeriod(
                1L, "01 01 2018", "10 01 2018");
        assertEquals(report, found.get(0));
    }
}
