package pavlo.restserver.service;


import pavlo.restserver.model.DateStatus;
import pavlo.restserver.model.Employee;

import java.util.List;

public interface DateStatusService {

    DateStatus findById(Long id);

    List<DateStatus> findAllSatuses();

    DateStatus save(DateStatus dateStatus);

    void update(DateStatus dateStatus);

    void delete(Long id);

    List<DateStatus> findByEmployee(Employee employee);

    List<DateStatus> findByEmployeeAndDate(Employee employee, String date);

    List<DateStatus> findByDate(String date);

    List<DateStatus> createDay(String date);

    void deleteAll();
}
