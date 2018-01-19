package pavlo.restserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pavlo.restserver.model.Report;

import java.util.*;

@Component
class TaskHolder {

    @Autowired
    private PositionService positionService;

    @Autowired
    private DateEventService dateEventService;

    @Autowired
    private DateStatusService dateStatusService;

    @Autowired
    private WorkReportService workReportService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EventService eventService;

    @Autowired
    private StatusWorkService statusWorkService;

    @Scheduled(cron = "0 0 8 * * *")
    public void newDay() {
        String date = "";
        dateEventService.createEventNewDay(date);
        dateStatusService.createDay(date);
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void reportMonth() {
        String date = "";
        workReportService.addAllReportForAllEmployeesFirstDayOfMonth(date);
        List<Report> reportList = workReportService.findAllReportForAllEmployeesFirstDayOfMonth(date);

        for (Report w : reportList) {
            workReportService.sendEmailOfPeriod(w);
        }
    }
}