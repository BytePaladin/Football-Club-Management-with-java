package footballclubmanagement;

/**
 *
 * @author Sabit
 */
import java.io.*;
import java.util.*;

public class FootballClubManagement {

    public static List<Player> players = new ArrayList<>();
    public static List<Coach> coaches = new ArrayList<>();
    public static List<Announcement> announcements = new ArrayList<>();
    public static List<Request> playerRequests = new ArrayList<>();
    public static List<Request> coachRequests = new ArrayList<>();
    public static Player loggedInPlayer=null;
    public static Coach loggedInCoach=null;
    public static Manager loggedInManager=null;

    
    
    
    
    public static Manager readManager() {

        try {
            Manager manager = null;
            FileReader fr = new FileReader("manager.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String name = parts[0];
                    String pass = parts[1];
                    manager = new Manager(new Person(name, pass));
                }
            }
            reader.close();
            return manager;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    
    
    

    public static void writeManager(String name, String password) {
        try {
            FileWriter fw = new FileWriter("manager.txt");
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(name + "," + password);
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    
    
    
    
    
    public static List<Player> readPlayer() {
        players.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("player.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9) { // Changed to 9 to accommodate health status field
                    String name = parts[0];
                    String pass = parts[1];
                    String position = parts[2];
                    int jerseyNumber = Integer.parseInt(parts[3]);
                    int id = Integer.parseInt(parts[4]);
                    String startDateStr = parts[5];
                    String endDateStr = parts[6];
                    double contractMoney = Double.parseDouble(parts[7]);
                    String healthStatus = parts[8];

                    Player player = new Player(new Person(name, pass), jerseyNumber, id, position, startDateStr, endDateStr, contractMoney, healthStatus);
                    players.add(player);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    
    
    
    
    
    
    public static void writePlayer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("player.txt"))) {
            for (Player player : players) {
                writer.write(player.getPerson().getName() + "," + player.getPerson().getPassword() + ","+ player.getPosition() + "," + player.getJerseyNumber() + "," + player.getId() + ","+ player.getStartDate() + "," + player.getEndDate() + "," + player.getContractMoney() + ","+ player.getHealthStatus() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    public static List<Coach> readCoach() {
        coaches.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("coach.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0];
                    String password = parts[1];
                    String startDate = parts[2];
                    String endDate = parts[3];

                    Coach coach = new Coach(new Person(name, password), startDate, endDate);
                    coaches.add(coach);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coaches;
    }

    
    
    
    
    
    
    public static void writeCoache() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("coach.txt"))) {
            for (Coach coach : coaches) {
                writer.write(coach.getPerson().getName() + "," + coach.getPerson().getPassword() + ","
                        + coach.getStartDate() + "," + coach.getEndDate() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    public static List<Announcement> readAnnouncements() {
        announcements.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("Announcements.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) { // Changed to 3 to accommodate date field
                    String announcement = parts[0];
                    String name = parts[1];
                    String date = parts[2];

                    Announcement a = new Announcement(announcement, name, date);
                    announcements.add(a);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return announcements;
    }
    
    
    
    
    
    

    public static void writeAnnouncement() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Announcements.txt"))) {
            for (Announcement a : announcements) {
                writer.write(a.getAnnouncement() + "," + a.getName() + "," + a.getDate() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    public static void addAnnouncement(Announcement a) {
        announcements.add(0, a);  // Add the announcement at the beginning of the list
        writeAnnouncement();
    }
    
    
    
    
    
    
    
    

    public static List<Request> readRequests(String filename) {
        if (filename.equals("PlayerRequests.txt")) {
            playerRequests.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String request = parts[0];
                        String name = parts[1];
                        String date = parts[2];

                        Request a = new Request(request, name, date);
                        playerRequests.add(a);
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return playerRequests;
        } else {
            coachRequests.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String request = parts[0];
                        String name = parts[1];
                        String date = parts[2];

                        Request a = new Request(request, name, date);
                        coachRequests.add(a);
                    }

                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return coachRequests;
        }

    }

    
    
    
    
    
    public static void writeRequest(String filename, List<Request> requests) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Request r : requests) {
                writer.write(r.getRequest() + "," + r.getName() + "," + r.getDate() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    

    public static void addNewPlayer(Player player) {
        players.add(player);
        writePlayer();
    }

    
    
    
    
    
    
    public static void addNewCoach(Coach coach) {
        coaches.add(coach);
        writeCoache();
    }

    
    
    
    
    
    
    public static void addPlayerRequest(Request r) {
        playerRequests.add(0, r);  // Add the request at the beginning of the list
        writeRequest("PlayerRequests.txt", playerRequests);
    }

    
    
    
    
    
    
    public static void addCoachRequest(Request r) {
        coachRequests.add(0, r);  // Add the request at the beginning of the list
        writeRequest("CoachRequests.txt", coachRequests);

    }

    
    
    
    
    
    
    public static Player searchPlayer(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    
    
    
    
    
    
    public static Coach searchCoach(String name) {
        for (Coach coach : coaches) {
            if (coach.getPerson().getName().equals(name)) {
                return coach;
            }
        }
        return null;
    }

    
    
    
    
    
    
    
    public static void deletePlayer(int id) {
        Player player = searchPlayer(id);
        if (player != null) {
            players.remove(player);
            writePlayer();
        }
    }

    
    
    
    
    
    
    public static void deleteCoach(String name) {
        Coach coach = searchCoach(name);
        if (coach != null) {
            coaches.remove(coach);
            writeCoache();
        }
    }

    
    
    
    
    
    public static void deleteAllPlayers() {
        players.clear();
        writePlayer();
    }

    
    
    
    
    
    public static void deleteAllCoaches() {
        coaches.clear();
        writeCoache();
    }
    
    
    
    
    
    

    public static boolean playerIdExists(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return true;
            }
        }
        return false;
    }

    
    
    
    
    
    
    public static boolean playerJerseyExists(int jerseyNumber) {
        for (Player player : players) {
            if (player.getJerseyNumber() == jerseyNumber) {
                return true;
            }
        }
        return false;
    }

    
    public static void main(String[] args) {
        WelcomePage welcome = new WelcomePage();
        welcome.show();
        
    }
}
