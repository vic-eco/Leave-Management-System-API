package cy.ac.ucy.cs.epl425.LMS.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.ac.ucy.cs.epl425.LMS.model.Employee;
import cy.ac.ucy.cs.epl425.LMS.model.Leave;
import cy.ac.ucy.cs.epl425.LMS.service.EmployeeService;
import cy.ac.ucy.cs.epl425.LMS.service.LeaveService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
    @Autowired
    LeaveService leaveService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves(@RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) Boolean approved){
        try{
            List<Leave> leaves = leaveService.getAllLeaves(startDate, endDate, approved);

            if(leaves.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(leaves, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable("id") Long id) {
        try {
        Leave leave = leaveService.getLeaveById(id);

        if(leave == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(leave, HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("employees/{eid}")
    public ResponseEntity<String> createLeave(@PathVariable("eid") Long eid, @RequestBody Leave leave){
        try{
            Employee employee = employeeService.getEmployeeById(eid);
            if (employee == null){
                return new ResponseEntity<>("Employee with id: "+eid+" not found", HttpStatus.NOT_FOUND);
            }

            if (isBlank(leave.getDescription())) {
                return new ResponseEntity<>("Description is required", HttpStatus.BAD_REQUEST);
            }
            if (leave.getStartDate() == null) {
                return new ResponseEntity<>("Start Date is required", HttpStatus.BAD_REQUEST);
            }
            if (leave.getEndDate() == null) {
                return new ResponseEntity<>("End Date is required", HttpStatus.BAD_REQUEST);
            }

            leave.setEmployeeId(eid);
            leaveService.createNewLeave(leave);
            
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLeave(@PathVariable("id") Long id, @RequestBody Leave updates){
        try{
            Leave updatedLeave = leaveService.updateLeave(id,  updates);
            if(updatedLeave == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllLeaves(){
        try{
           leaveService.deleteAllLeaves();
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveById(@PathVariable("id") long id){
        try{
           leaveService.deleteLeaveById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
