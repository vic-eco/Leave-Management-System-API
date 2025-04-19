package cy.ac.ucy.cs.epl425.LMS.repository;

import org.springframework.data.repository.CrudRepository;
import cy.ac.ucy.cs.epl425.LMS.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    //Did not add something here because things such as findById, findAll and save are inherited.
    //Only add here if I need something custom.
}
