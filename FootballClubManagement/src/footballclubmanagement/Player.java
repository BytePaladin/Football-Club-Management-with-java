package footballclubmanagement;

import java.time.LocalDate;

public class Player extends User implements Contract  {

    private int jerseyNumber,id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double contractMoney;
    private String healthStatus;

    public Player(Person person, int jerseyNumber, int id, String startDate, String endDate, double contractMoney, String healthStatus) {
        super(person);
        this.jerseyNumber = jerseyNumber;
        this.id = id;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.contractMoney = contractMoney;
        this.healthStatus = healthStatus;
    }



    public boolean isContractExpired() {
        LocalDate today = LocalDate.now();
        return today.isAfter(endDate);
    }

    public void updateHealthStatus(String update) {
        healthStatus = update;
    }

    public void updateContractEndDate() {
        LocalDate currentMonth = LocalDate.now();
        int nextMonth = (currentMonth.getMonthValue() % 12) + 1;
        int nextYear = currentMonth.getYear() + (nextMonth - 1) / 12;
        LocalDate nextStartDate = LocalDate.of(nextYear, nextMonth, 1);
        LocalDate nextEndDate = nextStartDate.plusDays(30);
        startDate = nextStartDate;
        endDate = nextEndDate;
    }

    public Person getPerson() {
        return super.getPerson();
    }

    public void setPerson(Person person){
        super.setPerson(person);
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
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

    public double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public String getHealthStatus() {
        return healthStatus;
    }
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public void getContractDetails() {
        
    }
    
    
}
