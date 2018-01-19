package pavlo.restserver.Service;


import pavlo.restserver.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findByTabelID(Long tabelID);

    Employee save(Employee employee);

    Employee getById(long id);

    List<Employee> getAll();

    void delete(long id);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByEmail(String email);

    void deleteAll();
}
