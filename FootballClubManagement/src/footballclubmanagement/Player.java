package footballclubmanagement;

/**
 *
 * @author Sabit
 */
import java.time.LocalDate;

public class Player extends User implements Contract {

    private int jerseyNumber, id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double contractMoney;
    private String healthStatus;
    private String position;
    private int contractDuration;

    public Player(Person person, int jerseyNumber, int id, String position, int contractDuration, double contractMoney, String healthStatus) {
        super(person);
        this.jerseyNumber = jerseyNumber;
        this.id = id;
        this.position = position;
        this.contractDuration = contractDuration;
        this.contractMoney = contractMoney;
        this.healthStatus = healthStatus;
        initializeContractDates();
    }

    public Player(Person person, int jerseyNumber, int id, String position, String startDate, String endDate, double contractMoney, String healthStatus) {
        super(person);
        this.jerseyNumber = jerseyNumber;
        this.id = id;
        this.position = position;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.contractMoney = contractMoney;
        this.healthStatus = healthStatus;
    }

    public void updateHealthStatus(String update) {
        healthStatus = update;
    }

    public void updateContractEndDate(int newContractDuration) {

        this.endDate = this.endDate.plusMonths(newContractDuration);
    }

    public double getContractMoney() {
        return contractMoney;
    }

    public Person getPerson() {
        return super.getPerson();
    }

    public void setPerson(Person person) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
    public void initializeContractDates() {
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusMonths(contractDuration);
    }

  

    @Override
    public String toString() {
        return "Player name : " + super.getPerson().getName() + ", Position = " + position + ", JerseyNumber = " + jerseyNumber + ", Id = " + id + ", Contract start date =" + startDate + ", Contract end date = " + endDate + ", ContractMoney = " + contractMoney + ", Health Status = " + healthStatus;
    }

    public String limitedToString() {
        return "Player name : " + super.getPerson().getName() + ", Position = " + position + ", JerseyNumber = " + jerseyNumber + ", Contract start date =" + startDate + ", contract end date = " + endDate + ", Health Status = " + healthStatus;
    }

}
