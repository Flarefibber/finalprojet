package pavlo.restserver.Service;


import pavlo.restserver.model.DateEvent;
import pavlo.restserver.model.Department;
import pavlo.restserver.model.Employee;
import pavlo.restserver.model.Report;
import pavlo.restserver.repository.DateEventRepository;
import pavlo.restserver.repository.DepartmentRepository;
import pavlo.restserver.repository.EmployeeRepository;
import pavlo.restserver.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DateEventServiceImpl implements DateEventService {

    @Autowired
    private DateEventRepository dateEventRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public static final SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MM yyyy");


    @Override
    public DateEvent findById(Long id) {
        return dateEventRepository.findOne(id);
    }

    @Override
    public DateEvent findByDate(String date) {
        DateEvent dateEvent = dateEventRepository.findByDate(date);
        if (dateEvent != null) {
            return dateEvent;
        }
        return null;
    }

    @Override
    public List<DateEvent> findAllEvents() {
        return dateEventRepository.findAll();
    }

    @Override
    public DateEvent save(DateEvent dateEvent) {
        DateEvent saveDateEvent = dateEventRepository.save(dateEvent);
        return saveDateEvent;
    }

    @Override
    public void update(DateEvent dateEvent) {
        dateEventRepository.save(dateEvent);
    }

    @Override
    public void delete(Long id) {
        if (dateEventRepository.findOne(id) != null) {
            dateEventRepository.delete(id);
        }
    }

    @Override
    public void deleteAll() {
        dateEventRepository.deleteAll();
    }


    @Override
    public List<DateEvent> findByEmployee(Employee employee) {

        return null;
    }

    @Override
    public List<DateEvent> findByEmployeeAndDate(Employee employee, String date) {
        return null;
    }

    @Override
    public List<DateEvent> findByDepartment(Department department) {
        return null;
    }

    @Override
    public DateEvent createEventNewDay(String date) {

        DateEvent dateEventReturn = new DateEvent();

        if ("".equals(date)) {
            date = formatForDateNow.format(new Date());
        }

        List<Employee> employeesList = employeeRepository.findAll();
        Set<Employee> employeeSet = new HashSet<>();
        for (Employee e : employeesList) {
            employeeSet.add(e);
        }

        List<Department> departmentList = departmentRepository.findAll();
        Set<Department> departmentSet = new HashSet<>();
        for (Department d : departmentList) {
            departmentSet.add(d);
        }
        String[] dateDayList = date.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(dateDayList[2]),
                Integer.parseInt(dateDayList[1]) - 1,
                Integer.parseInt(dateDayList[0]));

        if ((calendar.get(Calendar.DAY_OF_WEEK) != 1) && (calendar.get(Calendar.DAY_OF_WEEK) != 7)) {
            if (dateEventRepository.findByDate(date) == null) {
                dateEventReturn = dateEventRepository.save(new DateEvent(date, eventRepository.findOne(1L),
                        departmentSet, employeeSet));
            }
        }
        return dateEventReturn;
    }

    @Override
    public Report reportEmployeeOfPeriod(Employee employee, String dateBeginPeriod, String dateEndPeriod) {

        Report report = new Report();

        Employee employeeReport = employee;
        Float reportSalaryOfPeriod = 0f;
        List<String> workDays = new ArrayList<>();


        String[] beginPeriod = dateBeginPeriod.split(" ");
        String[] endPeriod = dateEndPeriod.split(" ");
        GregorianCalendar calendarEndPeriod = new GregorianCalendar();

        calendarEndPeriod.set(Integer.parseInt(endPeriod[2]),
                Integer.parseInt(endPeriod[1]) - 1,
                Integer.parseInt(endPeriod[0]));
        GregorianCalendar calendarBeginPeriod = new GregorianCalendar();
        calendarBeginPeriod.set(Integer.parseInt(beginPeriod[2]),
                Integer.parseInt(beginPeriod[1]) - 1,
                Integer.parseInt(beginPeriod[0]));

        long s = (calendarEndPeriod.getTime().getTime() - calendarBeginPeriod.getTime().getTime()) / 86400000;

        for (int i = 0; i < s + 1; i++) {
            if (i != 0) {
                calendarBeginPeriod.add(Calendar.DATE, 1);
            }

            String dateDay = ((calendarBeginPeriod.get(Calendar.DATE) < 10) ? ("0") : (""))
                    + calendarBeginPeriod.get(Calendar.DATE)
                    + (((calendarBeginPeriod.get(Calendar.MONTH) + 1) < 10) ? (" 0") : (" "))
                    + (calendarBeginPeriod.get(Calendar.MONTH) + 1)
                    + " "
                    + calendarBeginPeriod.get(Calendar.YEAR);
            Set<Employee> employeeSet = new HashSet<>();
            DateEvent dateEvent = dateEventRepository.findByDate(dateDay);

            if (dateEvent != null) {
                employeeSet = dateEvent.getEmployees();
                for (Employee e : employeeSet) {
                    if (employee.getId().equals(e.getId())) {

                        Float koef = 1f;
                        switch (dateEvent.getEvent().getType()) {
                            case "work":
                                koef = e.getKoefWork();
                            case "ill":
                                koef = e.getKoefIll();
                            case "holiday":
                                koef = e.getKoefHoliday();
                            case "not work":
                                koef = e.getKoefNotWork();
                        }
                        workDays.add(dateDay + " " + dateEvent.getEvent().getType() + " " + e.getPosition().getSalary()
                                + " " + koef.toString());
                        reportSalaryOfPeriod += e.getPosition().getSalary() * koef;
                    }
                }
            }
        }

        report.setTabelID(employee.getTabelID());
        report.setBeginPeriod(dateBeginPeriod);
        report.setEndPeriod(dateEndPeriod);

        report.setCountDay(workDays.size());
        report.setSalaryPeriod(reportSalaryOfPeriod);

        return report;
    }
}
