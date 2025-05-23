package cy.ac.ucy.cs.epl425.LMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.ac.ucy.cs.epl425.LMS.repository.LeaveRepository;
import cy.ac.ucy.cs.epl425.LMS.model.Leave;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired LeaveRepository leaveRepository;

    public List<Leave> getAllLeaves(LocalDate startDate, LocalDate endDate, Boolean approved){

        if (startDate == null && endDate == null && approved == null){
            List<Leave> leaves = new ArrayList<Leave>();
            this.leaveRepository.findAll().forEach(leaves::add);
            return leaves;
        }else if (approved != null){
            return approvedActive(startDate, endDate, approved);
        }else{
            return approvedInactive(startDate, endDate);
        }
    }

    private List<Leave> approvedActive(LocalDate startDate, LocalDate endDate, Boolean approved){

        if( startDate == null && endDate == null){
            return this.leaveRepository.findByApproved(approved);
        }else if (startDate != null && endDate == null){
            return this.leaveRepository.findByStartDateGreaterThanEqualAndApproved(startDate, approved);
        }else if (startDate == null && endDate != null){
            return this.leaveRepository.findByEndDateLessThanEqualAndApproved(endDate, approved);
        }else if (startDate != null && endDate != null){
            return this.leaveRepository.findByStartDateBetweenAndApproved(startDate, endDate, approved);
        }

        return Collections.emptyList();
    }

    private List<Leave> approvedInactive(LocalDate startDate, LocalDate endDate){

        if (startDate != null && endDate == null){
            return this.leaveRepository.findByStartDateGreaterThanEqual(startDate);
        }else if (startDate == null && endDate != null){
            return this.leaveRepository.findByEndDateLessThanEqual(endDate);
        }else if (startDate != null && endDate != null){
            return this.leaveRepository.findByStartDateBetween(startDate, endDate);
        }

        return Collections.emptyList();
    }

    public Leave getLeaveById(Long id) {
        Optional<Leave> leave = this.leaveRepository.findById(id);
        if(leave.isPresent())
            return leave.get();
        else 
            return null;
    }

    public void createNewLeave(Leave leave){
        this.leaveRepository.save(leave);
    }

    public Leave updateLeave(Long id, Leave updatedLeave){
        Optional<Leave> leaveLookup = this.leaveRepository.findById(id);

        if(leaveLookup.isPresent()){
            Leave leave = leaveLookup.get();

        if (updatedLeave.getApproved() != null){
            leave.setApproved(updatedLeave.getApproved());
        }
        if (updatedLeave.getDescription() != null){
            leave.setDescription(updatedLeave.getDescription());
        }
        if (updatedLeave.getEndDate() != null){
            leave.setEndDate(updatedLeave.getEndDate());
        }
        if (updatedLeave.getStartDate() != null){
            leave.setStartDate(updatedLeave.getStartDate());
        }

        return leaveRepository.save(leave);
        
        }else{
            return null;
        }
    }

    public void deleteAllLeaves(){
        this.leaveRepository.deleteAll();
    }

    public void deleteLeaveById(Long id){
        this.leaveRepository.deleteById(id);
    }
}