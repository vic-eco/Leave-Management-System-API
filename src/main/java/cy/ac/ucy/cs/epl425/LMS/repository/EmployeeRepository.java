package cy.ac.ucy.cs.epl425.LMS.repository;

import org.springframework.data.repository.CrudRepository;
import cy.ac.ucy.cs.epl425.LMS.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    List<Employee> findByDepartmentContaining(String department);
}
