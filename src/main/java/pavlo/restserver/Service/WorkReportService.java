package pavlo.restserver.Service;


import pavlo.restserver.model.Report;

import java.util.List;

public interface WorkReportService {

    Report add(Report report);

    List<Report> findAll();

    List<Report> findByTabelId(Long tabelID);

    List<Report> findReportByEmployeeAndBeginPeriodAndEndPeriod(Long tabelID, String beginPeriod, String endPeriod);

    List<Report> addAllReportForAllEmployeesFirstDayOfMonth(String month);

    List<Report> findAllReportForAllEmployeesFirstDayOfMonth(String month);

    void deleteAll();

    void sendEmailOfPeriod(Report report);
}
