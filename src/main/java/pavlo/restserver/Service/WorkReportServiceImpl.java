package pavlo.restserver.Service;

import pavlo.restserver.email.EmailService;
import pavlo.restserver.email.Mail;
import pavlo.restserver.model.Employee;
import pavlo.restserver.model.Report;
import pavlo.restserver.repository.EmployeeRepository;
import pavlo.restserver.repository.WorkReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WorkReportServiceImpl implements WorkReportService {
    public static final SimpleDateFormat formatForMonthNow = new SimpleDateFormat("MM yyyy");

    @Autowired
    private WorkReportRepository workReportRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DateEventService dateEventService;

    @Autowired
    private EmailService emailService;

    @Override
    public Report add(Report report) {
        if (report != null) {
            return workReportRepository.save(report);
        }
        return null;
    }

    @Override
    public List<Report> findAll() {
        return workReportRepository.findAll();
    }

    @Override
    public List<Report> findByTabelId(Long tabelID) {
        List<Report> list = workReportRepository.findByTabelID(tabelID);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<Report> findReportByEmployeeAndBeginPeriodAndEndPeriod(Long tabelID, String beginPeriod, String endPeriod) {
        List<Report> reports = workReportRepository.findByTabelIDAndBeginPeriodAndEndPeriod(tabelID, beginPeriod, endPeriod);
        return reports;
    }

    @Override
    public List<Report> addAllReportForAllEmployeesFirstDayOfMonth(String month) { //"" - previous month
        List<Report> reports = new ArrayList<>();
        String[] monthReportList = null;
        if ("".equals(month)) {
            month = formatForMonthNow.format(new Date());
            monthReportList = month.split(" ");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(Integer.parseInt(monthReportList[1]),
                    Integer.parseInt(monthReportList[0]) - 1,
                    Integer.parseInt("00"));
            month = calendar.get(Calendar.MONTH) + 1 + " " + calendar.get(Calendar.YEAR);
        }
        monthReportList = month.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(monthReportList[1]),
                Integer.parseInt(monthReportList[0]),
                Integer.parseInt("0"));
        String beginPeriod = "01"
                + (((calendar.get(Calendar.MONTH) + 1) < 10) ? (" 0") : (" "))
                + (calendar.get(Calendar.MONTH) + 1)
                + " "
                + calendar.get(Calendar.YEAR);
        String endPeriod = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? ("0") : (""))
                + calendar.get(Calendar.DAY_OF_MONTH)
                + (((calendar.get(Calendar.MONTH) + 1) < 10) ? (" 0") : (" "))
                + (calendar.get(Calendar.MONTH) + 1)
                + " "
                + calendar.get(Calendar.YEAR);

        List<Employee> employees = employeeRepository.findAll();

        if (!employees.isEmpty()) {
            for (Employee e : employees) {

                Report report = workReportRepository.save(dateEventService.reportEmployeeOfPeriod(e, beginPeriod, endPeriod));
                reports.add(report);
            }
        }
        return reports;
    }

    @Override
    public List<Report> findAllReportForAllEmployeesFirstDayOfMonth(String month) { //"" - previous month
        List<Report> reports = new ArrayList<>();
        String[] monthReportList = null;
        if ("".equals(month)) {
            month = formatForMonthNow.format(new Date());
            monthReportList = month.split(" ");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.set(Integer.parseInt(monthReportList[1]),
                    Integer.parseInt(monthReportList[0]) - 1,
                    Integer.parseInt("00"));
            month = calendar.get(Calendar.MONTH) + 1 + " " + calendar.get(Calendar.YEAR);
        }
        monthReportList = month.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(monthReportList[1]),
                Integer.parseInt(monthReportList[0]),
                Integer.parseInt("0"));
        String beginPeriod = "01"
                + (((calendar.get(Calendar.MONTH) + 1) < 10) ? (" 0") : (" "))
                + (calendar.get(Calendar.MONTH) + 1)
                + " "
                + calendar.get(Calendar.YEAR);
        String endPeriod = ((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? ("0") : (""))
                + calendar.get(Calendar.DAY_OF_MONTH)
                + (((calendar.get(Calendar.MONTH) + 1) < 10) ? (" 0") : (" "))
                + (calendar.get(Calendar.MONTH) + 1)
                + " "
                + calendar.get(Calendar.YEAR);

        List<Employee> employees = employeeRepository.findAll();

        if (!employees.isEmpty()) {
            for (Employee e : employees) {
                List<Report> list = workReportRepository.findByTabelIDAndBeginPeriodAndEndPeriod(e.getTabelID(), beginPeriod, endPeriod);
                Report report = list.get(list.size() - 1);
                reports.add(report);
            }
        }
        return reports;
    }

    @Override
    public void deleteAll() {
        workReportRepository.deleteAll();
    }

    @Override
    public void sendEmailOfPeriod(Report report) {
        Employee employee = employeeRepository.findByTabelID(report.getTabelID());
        Mail mail = new Mail();
        mail.setTo(employee.getEmail());
        mail.setSubject("Report period: " + report.getBeginPeriod() + " - " + report.getEndPeriod());
        String text = employee.getFirstName() + "." + employee.getLastName() + "\n"
                + "period: " + report.getBeginPeriod() + "-" + report.getEndPeriod() + "\n"
                + "count of work days: " + report.getCountDay() + "\n"
                + "RESULT salary: " + report.getSalaryPeriod();

        mail.setContent(text);

        emailService.sendSimpleMessage(mail);
    }


}
