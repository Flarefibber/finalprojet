package pavlo.restserver.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reports")
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee")
    private Long tabelID;

    @Column(name = "beginperiod")
    private String beginPeriod;

    @Column(name = "endperiod")
    private String endPeriod;

    @Column(name = "countday")
    private Integer countDay;

    @Column(name = "salaryperiod")
    private Float salaryPeriod;

    public Report() {
    }

    public Report(Long tabelID, String beginPeriod, String endPeriod) {
        this.tabelID = tabelID;
        this.beginPeriod = beginPeriod;
        this.endPeriod = endPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTabelID() {
        return tabelID;
    }

    public void setTabelID(Long tabelID) {
        this.tabelID = tabelID;
    }

    public String getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(String beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Integer getCountDay() {
        return countDay;
    }

    public void setCountDay(Integer countDay) {
        this.countDay = countDay;
    }

    public Float getSalaryPeriod() {
        return salaryPeriod;
    }

    public void setSalaryPeriod(Float salaryPeriod) {
        this.salaryPeriod = salaryPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report that = (Report) o;

        if (!id.equals(that.id)) return false;
        if (tabelID != null ? !tabelID.equals(that.tabelID) : that.tabelID != null) return false;
        if (beginPeriod != null ? !beginPeriod.equals(that.beginPeriod) : that.beginPeriod != null) return false;
        if (endPeriod != null ? !endPeriod.equals(that.endPeriod) : that.endPeriod != null) return false;
        if (countDay != null ? !countDay.equals(that.countDay) : that.countDay != null) return false;
        return salaryPeriod != null ? salaryPeriod.equals(that.salaryPeriod) : that.salaryPeriod == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (tabelID != null ? tabelID.hashCode() : 0);
        result = 31 * result + (beginPeriod != null ? beginPeriod.hashCode() : 0);
        result = 31 * result + (endPeriod != null ? endPeriod.hashCode() : 0);
        result = 31 * result + (countDay != null ? countDay.hashCode() : 0);
        result = 31 * result + (salaryPeriod != null ? salaryPeriod.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", tabelID=" + tabelID +
                ", beginPeriod='" + beginPeriod + '\'' +
                ", endPeriod='" + endPeriod + '\'' +
                ", countDay=" + countDay +
                ", salaryPeriod=" + salaryPeriod +
                '}';
    }
}
