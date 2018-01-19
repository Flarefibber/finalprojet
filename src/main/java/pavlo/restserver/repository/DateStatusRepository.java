package pavlo.restserver.repository;

import pavlo.restserver.model.DateStatus;
import pavlo.restserver.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateStatusRepository extends JpaRepository<DateStatus, Long> {

    List<DateStatus> findByEmployee(Employee employee);

    List<DateStatus> findByDateAndEmployee(String date, Employee employee);

    List<DateStatus> findByDate(String date);
}
