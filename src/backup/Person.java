
package footballclubmanagement;

/**
 *
 * @author Sabit
 */
public class Person {
     private String name;
    private long ID;

    public Person(String name, long iD) {
        this.name = name;
        this.ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
