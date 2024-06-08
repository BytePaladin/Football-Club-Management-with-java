package footballclubmanagement;

/**
 *
 * @author Sabit
 */
public class Manager  extends User{
    private String name , password;
   
    public Manager(Person person){
        super(person);
    }

    public String getName() {
        return super.getPerson().getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" + "name=" + super.getPerson().getName() + ", password=" + super.getPerson().getPassword() + '}';
    }

    
    
    
}
