package pavlo.restserver.repository;

import pavlo.restserver.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByTabelID(Long tabelID);

    List<Employee> findByEmail(String email);

}
