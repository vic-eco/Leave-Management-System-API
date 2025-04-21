package cy.ac.ucy.cs.epl425.LMS.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import cy.ac.ucy.cs.epl425.LMS.model.Leave;

public interface LeaveRepository extends CrudRepository<Leave, Long>{
    List<Leave> findByStartDateGreaterThanEqual(LocalDate startDate);
    List<Leave> findByEndDateLessThanEqual(LocalDate endDate);
    List<Leave> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Leave> findByApproved(Boolean approved);   

    List<Leave> findByStartDateGreaterThanEqualAndApproved(LocalDate startDate, Boolean approved);
    List<Leave> findByEndDateLessThanEqualAndApproved(LocalDate endDate, Boolean approved);
    List<Leave> findByStartDateBetweenAndApproved(LocalDate startDate, LocalDate endDate, Boolean approved);
}
