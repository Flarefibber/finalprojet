package pavlo.restserver.service;


import pavlo.restserver.model.DateEvent;
import pavlo.restserver.model.Department;
import pavlo.restserver.model.Employee;
import pavlo.restserver.model.Report;

import java.util.List;

public interface DateEventService {

    DateEvent findById(Long id);

    DateEvent findByDate(String date);

    List<DateEvent> findAllEvents();

    DateEvent save(DateEvent dateEvent);

    void update(DateEvent dateEvent);

    void delete(Long id);

    List<DateEvent> findByEmployee(Employee employee);

    List<DateEvent> findByEmployeeAndDate(Employee employee, String date);

    List<DateEvent> findByDepartment(Department department);

    DateEvent createEventNewDay(String date);

    Report reportEmployeeOfPeriod(Employee employee, String dateBeginPeriod, String dateEndPeriod);

    void deleteAll();
}
