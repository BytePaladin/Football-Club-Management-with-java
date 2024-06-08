package footballclubmanagement;

public class Request {
    private String request , name , date ;

    public Request(String request, String name, String date) {
        this.request = request;
        this.name = name;
        this.date = date;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
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
        return "Request = " + request + ", name = " + name + ", date = " + date ;
    }
    
}
