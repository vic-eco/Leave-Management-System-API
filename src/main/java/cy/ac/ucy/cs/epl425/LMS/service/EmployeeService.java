package cy.ac.ucy.cs.epl425.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;

import cy.ac.ucy.cs.epl425.LMS.repository.EmployeeRepository;
import cy.ac.ucy.cs.epl425.LMS.model.Employee;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


public class EmployeeService {

    @Autowired EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        this.employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public List<Employee> getAllEmployeesByDept(){
        List<Employee> employees = new ArrayList<Employee>();
        this.employeeRepository.findByDepartment().forEach(employees::add);
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        if(employee.isPresent())
            return employee.get();
        else return null;
    }

    public void createNewEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee){
        Optional<Employee> employeeLookup = this.employeeRepository.findById(id);

        if(employeeLookup.isPresent()){
            Employee employee = employeeLookup.get();

        if (updatedEmployee.getFirstName() != null){
            employee.setFirstName(updatedEmployee.getFirstName());
        }
        if (updatedEmployee.getLastName() != null){
            employee.setLastName(updatedEmployee.getLastName());
        }
        if (updatedEmployee.getDateOfBirth() != null){
            employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
        }
        if (updatedEmployee.getDepartment() != null){
            employee.setDepartment(updatedEmployee.getDepartment());
        }

        return employeeRepository.save(employee);
        }else{
            return null;
        }
    }

    public void deleteAllEmployees(){
        this.employeeRepository.deleteAll();
    }

    public void deleteEmployeeById(Long id){
        this.employeeRepository.deleteById(id);
    }
}