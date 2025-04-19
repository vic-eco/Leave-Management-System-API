package cy.ac.ucy.cs.epl425.LMS.repository;

import org.springframework.data.repository.CrudRepository;
import cy.ac.ucy.cs.epl425.LMS.model.Employee;

public interface LeaveRepository extends CrudRepository<Employee, Long>{

}
