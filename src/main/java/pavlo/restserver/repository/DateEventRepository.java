package pavlo.restserver.repository;

import pavlo.restserver.model.DateEvent;
import pavlo.restserver.model.Department;
import pavlo.restserver.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateEventRepository extends JpaRepository<DateEvent, Long> {

    List<DateEvent> findByEmployees(List<Employee> employees);

    List<DateEvent> findByEmployeesAndDate(List<Employee> employees, String date);

    List<DateEvent> findByDepartments(List<Department> departments);

    DateEvent findByDate(String date);
}
