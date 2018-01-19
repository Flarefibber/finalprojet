package pavlo.restserver.service;

import pavlo.restserver.model.Department;
import pavlo.restserver.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id) {
        Department department = departmentRepository.findOne(id);
        if (department != null) {
            return department;
        }
        return null;
    }

    @Override
    public List<Department> findAllDepartment() {
        List<Department> list = departmentRepository.findAll();
        if (list != null) {
            return list;
        }
        return null;
    }

    @Override
    public List<Department> findByName(String name) {
        List<Department> list = departmentRepository.findByName(name);

        if (list.size() > 0) {
            return list;
        }

        return null;
    }

    @Override
    public Department save(Department department) {
        Department saveDepartment = departmentRepository.save(department);
        return saveDepartment;
    }

    @Override
    public void update(Department department) {
        Department department1 = departmentRepository.findOne(department.getId());
        if (department1 != null) {
            departmentRepository.delete(department.getId());
        }
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        Department department1 = departmentRepository.findOne(id);
        if (department1 != null) {
            departmentRepository.delete(id);
        }
    }

    @Override
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}
