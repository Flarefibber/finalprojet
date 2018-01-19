package pavlo.restserver.Service;

import pavlo.restserver.model.DateStatus;
import pavlo.restserver.model.Employee;
import pavlo.restserver.model.StatusWork;
import pavlo.restserver.repository.DateStatusRepository;
import pavlo.restserver.repository.EmployeeRepository;
import pavlo.restserver.repository.StatusWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DateStatusServiceImpl implements DateStatusService {

    @Autowired
    private DateStatusRepository dateStatusRepository;


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StatusWorkRepository statusWorkRepository;

    public static final SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd MM yyyy");

    @Override
    public DateStatus findById(Long id) {
        return dateStatusRepository.findOne(id);
    }

    @Override
    public List<DateStatus> findAllSatuses() {
        return dateStatusRepository.findAll();
    }

    @Override
    public DateStatus save(DateStatus dateStatus) {
        DateStatus saveDateStatus = dateStatusRepository.save(dateStatus);
        return saveDateStatus;
    }

    @Override
    public void update(DateStatus dateStatus) {
        dateStatusRepository.save(dateStatus);
    }

    @Override
    public void delete(Long id) {
        if (dateStatusRepository.findOne(id) != null) {
            dateStatusRepository.delete(id);
        }
    }

    @Override
    public void deleteAll() {
        dateStatusRepository.deleteAll();
    }

    @Override
    public List<DateStatus> findByEmployee(Employee employee) {
        return dateStatusRepository.findByEmployee(employee);
    }

    @Override
    public List<DateStatus> findByEmployeeAndDate(Employee employee, String date) {
        return dateStatusRepository.findByDateAndEmployee(date, employee);
    }

    @Override
    public List<DateStatus> findByDate(String date) {
        return dateStatusRepository.findByDate(date);
    }

    @Override
    public List<DateStatus> createDay(String date) {
        List<DateStatus> saveDataStatusList = new ArrayList<>();

        if ("".equals(date)) {
            date = formatForDateNow.format(new Date());
        }

        List<Employee> employeesList = employeeRepository.findAll();
        Set<Employee> employeeSet = new HashSet<>();
        for (Employee e : employeesList) {
            employeeSet.add(e);
        }

        String[] dateDayList = date.split(" ");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Integer.parseInt(dateDayList[2]),
                Integer.parseInt(dateDayList[1]) - 1,
                Integer.parseInt(dateDayList[0]));

        if ((calendar.get(Calendar.DAY_OF_WEEK) != 1) && (calendar.get(Calendar.DAY_OF_WEEK) != 7)) {
            StatusWork statusWork = statusWorkRepository.findByName("work");

            for (Employee e : employeeSet) {

                List<DateStatus> list = dateStatusRepository.findByDateAndEmployee(date, e);
                if ((list == null) || (list.size() == 0)) {
                    saveDataStatusList.add(dateStatusRepository.save(new DateStatus(e,
                            date, statusWork))
                    );
                }
            }
        }
        List<DateStatus> dateStatusListReturn = dateStatusRepository.findByDate(date);

        return dateStatusListReturn;
    }
}
