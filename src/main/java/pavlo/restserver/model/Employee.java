package pavlo.restserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tabelid")
    private Long tabelID;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @Column(name = "koefwork")
    private Float koefWork;

    @Column(name = "koefill")
    private Float koefIll;

    @Column(name = "koefholiday")
    private Float koefHoliday;

    @Column(name = "koefnotwork")
    private Float koefNotWork;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "employee")
    List<DateStatus> statuses;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "employees")
    List<DateEvent> events;

    public Employee() {
    }

    public Employee(Long tabelID, String firstName, String lastName, String email) {
        this.tabelID = tabelID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee(Long tabelID, String firstName, String lastName, String email,
                    Department department, Position position,
                    Float koefWork, Float koefIll, Float koefHoliday, Float koefNotWork) {
        this.tabelID = tabelID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.position = position;
        this.koefWork = koefWork;
        this.koefIll = koefIll;
        this.koefHoliday = koefHoliday;
        this.koefNotWork = koefNotWork;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Float getKoefWork() {
        return koefWork;
    }

    public void setKoefWork(Float koefWork) {
        this.koefWork = koefWork;
    }

    public Float getKoefIll() {
        return koefIll;
    }

    public void setKoefIll(Float koefIll) {
        this.koefIll = koefIll;
    }

    public Float getKoefHoliday() {
        return koefHoliday;
    }

    public void setKoefHoliday(Float koefHoliday) {
        this.koefHoliday = koefHoliday;
    }

    public Float getKoefNotWork() {
        return koefNotWork;
    }

    public void setKoefNotWork(Float koefNotWork) {
        this.koefNotWork = koefNotWork;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", tabelID=" + tabelID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", position=" + position +
                ", koefWork=" + koefWork +
                ", koefHoliday=" + koefHoliday +
                ", koefIll=" + koefIll +
                ", koefNotWork=" + koefNotWork +
                '}';
    }
}
