package footballclubmanagement;

/**
 *
 * @author Sabit
 */
public abstract class User {
    private Person person;

    public User(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
