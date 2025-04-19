package cy.ac.ucy.cs.epl425.LMS.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import cy.ac.ucy.cs.epl425.LMS.model.Leave;

public interface LeaveRepository extends CrudRepository<Leave, Long>{
    List<Leave> findByDateGreaterThanEqual(LocalDate startDate);
    List<Leave> findByDateLessThanEqual(LocalDate endDate);
    List<Leave> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Leave> findByApproved(Boolean approved);   

    List<Leave> findByDateGreaterThanEqualAndApproved(LocalDate startDate, Boolean approved);
    List<Leave> findByDateLessThanEqualAndApproved(LocalDate endDate, Boolean approved);
    List<Leave> findByDateBetweenAndApproved(LocalDate startDate, LocalDate endDate, Boolean approved);
}
