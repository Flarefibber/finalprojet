package pavlo.restserver.service;

import pavlo.restserver.model.Employee;
import pavlo.restserver.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getById(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(long id) {
        employeeRepository.delete(id);
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    @Override
    public Employee findByTabelID(Long tabelID) {
        Employee list = employeeRepository.findByTabelID(tabelID);
        if (list != null) {
            return list;
        }
        return null;
    }
}
