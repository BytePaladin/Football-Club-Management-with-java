package footballclubmanagement;

public class Request {

    private String request;
    private String name;
    private String date;

    public Request(String request, String name, String date) {
        this.request = request;
        this.name = name;
        this.date = date;
    }

    public String getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Request = " + request  + ", From = " + name +  ", date= " + date + "\n";
    }
}
