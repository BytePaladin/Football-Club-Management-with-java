package footballclubmanagement;

import java.time.LocalDate;

public class Coach extends User implements Contract {
    private LocalDate startDate;
    private LocalDate endDate;

     public Coach(Person person, String startDate, String endDate){
         super(person);
         this.startDate = LocalDate.parse(startDate);
         this.endDate = LocalDate.parse(endDate);
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
    public void getContractDetails() {
        
    }
   
}
