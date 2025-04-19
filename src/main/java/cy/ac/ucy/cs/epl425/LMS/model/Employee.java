package cy.ac.ucy.cs.epl425.LMS.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("employees")
public class Employee {
    
    @Id
    private Long id;

    @Column("firtname")
    private String firstname;

    @Column("lastname")
    private String lastname;

    @Column("department")
    private String department;

    @Column("date_of_birth")
    private Date dateOfBirth;
    
    public Employee(){

    }

    public Employee(String firstname, String lastname, String department, Date dateOfBirth){
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
    }

    //getters
    public Long getId(){
        return id;
    }

    public String getFirstName(){
        return firstname;
    }

    public String getLastName(){
        return lastname;
    }

    public String getDepartment(){
        return department;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    //setters
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }

    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}
