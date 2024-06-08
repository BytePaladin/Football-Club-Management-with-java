package footballclubmanagement;

import java.time.LocalDate;

public class Coach extends User implements Contract {
    private LocalDate startDate;
    private LocalDate endDate;
    private int contractDuration;

   
    public Coach(Person person, int contractDuration){
         super(person);
         this.contractDuration = contractDuration;
         initializeContractDates();
     } 
     
    
    public Coach(Person person, String startDate, String endDate){
         super(person);
         this.startDate = LocalDate.parse(startDate);
         this.endDate = LocalDate.parse(endDate);
     }

    @Override
    public void initializeContractDates() {
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusMonths(contractDuration);
    }

    
    public boolean isContractExpired() {
        LocalDate today = LocalDate.now();
        return today.isAfter(endDate);
    }

    
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Coach{" + "startDate=" + startDate + ", endDate=" + endDate + ", contractDuration=" + contractDuration + '}';
    }

    
}
