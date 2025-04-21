package cy.ac.ucy.cs.epl425.LMS.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.relational.core.mapping.Column;

@Table("leaves")
public class Leave {
    
    @Id
    private Long id;

    @JsonProperty("employeeId")
    @Column("employee_id")
    private Long employeeId;

    @JsonProperty("description")
    @Column("description")
    private String description;

    @JsonProperty("startDate")
    @Column("start_date")
    private LocalDate startDate;

    @JsonProperty("endDate")
    @Column("end_date")
    private LocalDate endDate;

    @JsonProperty("approved")
    @Column("approved")
    private Boolean approved;
    
    public Leave(){

    }

    public Leave(Long employeeId, String description, LocalDate startDate, LocalDate endDate, Boolean approved){
        this.employeeId = employeeId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
    }

    //getters
    public Long getId(){
        return id;
    }

    public Long getEmployeeId(){
        return employeeId;
    }

    public String getDescription(){
        return description;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public Boolean getApproved(){
        return approved;
    }

    //setters
    public void setEmployeeId(Long eid){
        this.employeeId = eid;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public void setApproved(Boolean approved){
        this.approved = approved;
    }

//     @Override
//     public String toString() {
//     return "Leave [id=" + id + ", employee=" + employeeId + ", description =" + description + "]";
// }


}
