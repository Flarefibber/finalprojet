package pavlo.restserver.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "datestatuses")
public class DateStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "datestatus")
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private StatusWork statusWork;

    public DateStatus() {
    }

    public DateStatus(String date, StatusWork statusWork) {
        this.date = date;
        this.statusWork = statusWork;
    }

    public DateStatus(Employee employee, String date, StatusWork statusWork) {
        this.employee = employee;
        this.date = date;
        this.statusWork = statusWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public StatusWork getStatusWork() {
        return statusWork;
    }

    public void setStatusWork(StatusWork statusWork) {
        this.statusWork = statusWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateStatus that = (DateStatus) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return statusWork != null ? statusWork.equals(that.statusWork) : that.statusWork == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (statusWork != null ? statusWork.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DateStatus{" +
                "id=" + id +
                ", employee=" + employee.getLastName() +
                ", date=" + date +
                ", statusWork=" + statusWork +
                '}';
    }
}
