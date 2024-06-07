
package footballclubmanagement;

import java.io.*;
import java.util.*;

public class FootballClubManagement {

    public static int index = 0;
    public static List<Player> players = new ArrayList<>();
    public static List<Coach> coaches = new ArrayList<>();
    
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
                    
                    manager = new Manager(new Person(name,pass));

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

    public static void readPlayer() {
        try (BufferedReader reader = new BufferedReader(new FileReader("player.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    String name = parts[0];
                    String pass = parts[1];
                    String position = parts[2];
                    int jerseyNumber = Integer.parseInt(parts[3]);
                    int id = Integer.parseInt(parts[4]);
                    String startDateStr = parts[5];
                    String endDateStr = parts[6];
                    double contractMoney = Double.parseDouble(parts[7]);
                    String healthStatus = parts[8];

                    Player player = new Player(new Person(name, pass), jerseyNumber, id , position, startDateStr, endDateStr, contractMoney, healthStatus);
                    players.add(player);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writePlayer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("player.txt"))) {
            for (Player player : players) {
                writer.write(player.getPerson().getName() + "," + player.getPerson().getPassword() + ","
                        + player.getJerseyNumber() + "," +player.getId()+","+player.getPosition()+","+ player.getStartDate() + ","
                        + player.getEndDate() + "," + player.getContractDetails() + "," + player.getHealthStatus()
                        + "\n");
              
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCoach() {
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
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCoachesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("coach.txt"))) {
            for (Coach coach : coaches) {
                writer.write(coach.getPerson().getName() + "," + coach.getPerson().getPassword() + ","
                        + coach.getStartDate() + "," + coach.getEndDate() + "\n");
            }
            writer.close();
            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Manager manager = readManager();
        System.out.println(manager.toString());

    }

}
