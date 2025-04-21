package cy.ac.ucy.cs.epl425.LMS.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.relational.core.mapping.Column;

@Table("employees")
public class Employee {
    
    @Id
    private Long id;

    @JsonProperty("firstname")
    @Column("firstname")
    private String firstName;

    @JsonProperty("lastname")
    @Column("lastname")
    private String lastName;

    @JsonProperty("department")
    @Column("department")
    private String department;

    @JsonProperty("dateOfBirth")
    @Column("date_of_birth")
    private Date dateOfBirth;
    
    public Employee(){

    }

    public Employee(String firstname, String lastname, String department, Date dateOfBirth){
        this.firstName = firstname;
        this.lastName = lastname;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
    }

    //getters
    public Long getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getDepartment(){
        return department;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    //setters
    public void setFirstName(String firstname){
        this.firstName = firstname;
    }

    public void setLastName(String lastname){
        this.lastName = lastname;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}
