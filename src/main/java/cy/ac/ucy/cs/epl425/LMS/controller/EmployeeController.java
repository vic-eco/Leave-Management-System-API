package cy.ac.ucy.cs.epl425.LMS.controller;

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
import cy.ac.ucy.cs.epl425.LMS.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String department) {
        try {
        List<Employee> employees;
        
        if (department == null){
            employees  = employeeService.getAllEmployees();
        }else{
            employees  = employeeService.getAllEmployeesByDept(department);
        }

        if(employees.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(employees, HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        try {
        Employee employee = employeeService.getEmployeeById(id);

        if(employee == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(employee, HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        try{
            if (isBlank(employee.getFirstName())) {
                return new ResponseEntity<>("First name is required", HttpStatus.BAD_REQUEST);
            }
            if (isBlank(employee.getLastName())) {
                return new ResponseEntity<>("Last name is required", HttpStatus.BAD_REQUEST);
            }
            if (isBlank(employee.getDepartment())) {
                return new ResponseEntity<>("Department is required", HttpStatus.BAD_REQUEST);
            }
            if (employee.getDateOfBirth() == null) {
                return new ResponseEntity<>("Date of Birth is required", HttpStatus.BAD_REQUEST);
            }

            employeeService.createNewEmployee(employee);
            
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee updates){
        try{
            Employee updatedEmp = employeeService.updateEmployee(id,  updates);
            if(updatedEmp == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllEmployees(){
        try{
           employeeService.deleteAllEmployees();
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") long id){
        try{
           employeeService.deleteEmployeeById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
    
}