
package footballclubmanagement;

/**
 *
 * @author Sabit
 */
public class Announcement {
    private String announcement , name , date;

    public Announcement(String announcement, String name, String date) {
        this.announcement = announcement;
        this.name = name;
        this.date = date;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Announcement : " + announcement + ", from = " + name + ", date = " + date ;
    }
    
    
}
