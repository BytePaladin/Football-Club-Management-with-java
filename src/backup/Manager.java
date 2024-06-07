package footballclubmanagement;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

abstract class Person {
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

public class Manager extends Person implements View, Update, Announcements {

    public Manager(String name, long ID) {
        super(name, ID);
    }

    public static void SignInNewPlayers() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the new player:");
        String newPlayerName = input.nextLine();

        System.out.println("Enter the player's ID:");
        long newPlayerID = input.nextLong();
        input.nextLine(); 

        System.out.println("Enter the new player's jersey number:");
        int newPlayerJersey = input.nextInt();
        input.nextLine(); 
        
        System.out.println("Enter the new player's position:");
        String newPlayerPosition = input.nextLine();

        System.out.println("Enter the contract end date (month, year):");
        int month = input.nextInt();
        int year = input.nextInt();
        input.nextLine(); // Consume the leftover newline character
        LocalDate contractEndDate = LocalDate.of(year, month, 1); // Set day to 1

        System.out.println("Enter the contract payment:");
        int contractPayment = input.nextInt();
        input.nextLine(); // Consume the leftover newline character

        System.out.println("Enter the player's health:");
        String playerHealth = input.nextLine();

        LocalDateTime dateTimeOfEntry = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Use try-with-resources to ensure BufferedWriter is closed properly
        try (BufferedWriter writeNewPlayer = new BufferedWriter(new FileWriter("Player.txt", true))) {
            writeNewPlayer.write("Name: " + newPlayerName);
            writeNewPlayer.newLine();
            writeNewPlayer.write("ID: " + newPlayerID);
            writeNewPlayer.newLine();
            writeNewPlayer.write("Jersey Number: " + newPlayerJersey);
            writeNewPlayer.newLine();
            writeNewPlayer.write("Player Position: " + newPlayerPosition);
            writeNewPlayer.newLine();
            writeNewPlayer.write("Contract End Date: " + contractEndDate);
            writeNewPlayer.newLine();
            writeNewPlayer.write("Contract Payment: " + contractPayment);
            writeNewPlayer.newLine();
            writeNewPlayer.write("Player's Health: " + playerHealth);
            writeNewPlayer.newLine();
            writeNewPlayer.write("Date and Time of Entry: " + dateTimeOfEntry.format(formatter));
            writeNewPlayer.newLine();
            writeNewPlayer.write("_______________________________________________________________________________***");
            writeNewPlayer.newLine();
            writeNewPlayer.newLine(); // Add an extra line for separation between entries
            System.out.println("Successfully signed in the new player.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void SignInNewCoachs() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the new coach:");
        String newCoachName = input.nextLine();

        System.out.println("Enter the coach's ID:");
        long newCoachID = input.nextLong();
        input.nextLine(); // Consume the leftover newline character

        System.out.println("Enter the contract end date (month, year):");
        int month = input.nextInt();
        int year = input.nextInt();
        input.nextLine(); // Consume the leftover newline character
        LocalDate contractEndDate = LocalDate.of(year, month, 1); // Set day to 1

        System.out.println("Enter the contract payment:");
        int contractPayment = input.nextInt();
        input.nextLine(); // Consume the leftover newline character

        LocalDateTime dateTimeOfEntry = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Use try-with-resources to ensure BufferedWriter is closed properly
        try (BufferedWriter writeNewCoach = new BufferedWriter(new FileWriter("Coach.txt", true))) {
            writeNewCoach.write("Name: " + newCoachName);
            writeNewCoach.newLine();
            writeNewCoach.write("ID: " + newCoachID);
            writeNewCoach.newLine();
            writeNewCoach.write("Contract End Date: " + contractEndDate);
            writeNewCoach.newLine();
            writeNewCoach.write("Contract Payment: " + contractPayment);
            writeNewCoach.newLine();
            writeNewCoach.write("Date and Time of Entry: " + dateTimeOfEntry.format(formatter));
            writeNewCoach.newLine();
            writeNewCoach.write("_______________________________________________________________________________***");
            writeNewCoach.newLine(); // Add an extra line for separation between entries
            System.out.println("Successfully signed in the new coach.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void TerminateContractPlayer() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the player to terminate:");
        String playerNameToTerminate = input.nextLine();

        System.out.println("Enter the ID of the player to terminate:");
        long playerIDToTerminate = input.nextLong();
        input.nextLine(); // Consume the leftover newline character

        File inputFile = new File("Player.txt");
        File tempFile = new File("Player_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean skipLines = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ") && line.equals("Name: " + playerNameToTerminate)) {
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("ID: " + playerIDToTerminate)) {
                        skipLines = true;
                    } else {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                    }
                } else if (!skipLines) {
                    writer.write(line);
                    writer.newLine();
                }

                if (skipLines && line.contains("_______________________________________________________________________________***")) {
                    skipLines = false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temporary file");
        } else {
            System.out.println("Successfully terminated the player's contract.");
        }

        System.out.println("Do you want to terminate another player? (yes/no)");
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            TerminateContractPlayer();
        }
    }

    public static void TerminateContractCoach() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the coach to terminate:");
        String coachNameToTerminate = input.nextLine();

        System.out.println("Enter the ID of the coach to terminate:");
        long coachIDToTerminate = input.nextLong();
        input.nextLine(); // Consume the leftover newline character

        File inputFile = new File("Coach.txt");
        File tempFile = new File("Coach_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean skipLines = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ") && line.equals("Name: " + coachNameToTerminate)) {
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("ID: " + coachIDToTerminate)) {
                        skipLines = true;
                    } else {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                    }
                } else if (!skipLines) {
                    writer.write(line);
                    writer.newLine();
                }

                if (skipLines && line.contains("_______________________________________________________________________________***")) {
                    skipLines = false;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temporary file");
        } else {
            System.out.println("Successfully terminated the coach's contract.");
        }

        System.out.println("Do you want to terminate another coach? (yes/no)");
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            TerminateContractCoach();
        }
    }

    @Override
    public void view() {
        viewPlayer();
    }

    public void viewPlayer() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Player.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select update type:");
        System.out.println("1. Update Player Jersey");
        System.out.println("2. Update Player Contract");
        System.out.println("3. Update Coach Contract");
        int choice = input.nextInt();
        input.nextLine(); // Consume the leftover newline character

        switch (choice) {
            case 1:
                updatePlayerJersey();
                break;
            case 2:
                updatePlayerContract();
                break;
            case 3:
                updateCoachContract();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    public void updatePlayerJersey() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the player to update:");
        String playerNameToUpdate = input.nextLine();

        System.out.println("Enter the ID of the player to update:");
        long playerIDToUpdate = input.nextLong();
        input.nextLine(); // Consume the leftover newline character

        System.out.println("Enter the new jersey number:");
        int newJerseyNumber = input.nextInt();
        input.nextLine(); // Consume the leftover newline character

        File inputFile = new File("Player.txt");
        File tempFile = new File("Player_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean updateJersey = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ") && line.equals("Name: " + playerNameToUpdate)) {
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("ID: " + playerIDToUpdate)) {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                        reader.readLine(); // Skip the current jersey number line
                        writer.write("Jersey Number: " + newJerseyNumber);
                        writer.newLine();
                        // Write other details as is
                        while (!(line = reader.readLine()).contains("_______________________________________________________________________________***")) {
                            writer.write(line);
                            writer.newLine();
                        }
                        writer.write(line); // Write the separator line
                        writer.newLine();
                        updateJersey = true;
                    } else {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                    }
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!updateJersey) {
                System.out.println("No matching entry found for the given name and ID.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temporary file");
        } else {
            System.out.println("Successfully updated the player's jersey number.");
        }
    }

    public void updatePlayerContract() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the player to update:");
        String playerNameToUpdate = input.nextLine();

        System.out.println("Enter the ID of the player to update:");
        long playerIDToUpdate = input.nextLong();
        input.nextLine(); // Consume the leftover newline character

        System.out.println("Enter the new contract end date (month, year):");
        int month = input.nextInt();
        int year = input.nextInt();
        input.nextLine(); // Consume the leftover newline character
        LocalDate newContractEndDate = LocalDate.of(year, month, 1); // Set day to 1

        System.out.println("Enter the new contract payment:");
        int newContractPayment = input.nextInt();
        input.nextLine(); // Consume the leftover newline character

        File inputFile = new File("Player.txt");
        File tempFile = new File("Player_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean updateContract = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ") && line.equals("Name: " + playerNameToUpdate)) {
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("ID: " + playerIDToUpdate)) {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                        writer.write(reader.readLine()); // Write the jersey number
                        writer.newLine();
                        writer.write(reader.readLine()); // Write the player position
                        writer.newLine();
                        reader.readLine(); // Skip the current contract end date line
                        reader.readLine(); // Skip the current contract payment line
                        writer.write("Contract End Date: " + newContractEndDate);
                        writer.newLine();
                        writer.write("Contract Payment: " + newContractPayment);
                        writer.newLine();
                        writer.write(reader.readLine()); // Write the player's health line
                        writer.newLine();
                        writer.write(reader.readLine()); // Write the date and time of entry line
                        writer.newLine();
                        writer.write(reader.readLine()); // Write the separator line
                        writer.newLine();
                        updateContract = true;
                    } else {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                    }
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!updateContract) {
                System.out.println("No matching entry found for the given name and ID.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temporary file");
        } else {
            System.out.println("Successfully updated the player's contract.");
        }
    }

    public void updateCoachContract() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the name of the coach to update:");
        String coachNameToUpdate = input.nextLine();

        System.out.println("Enter the ID of the coach to update:");
        long coachIDToUpdate = input.nextLong();
        input.nextLine(); // Consume the leftover newline character

        System.out.println("Enter the new contract end date (month, year):");
        int month = input.nextInt();
        int year = input.nextInt();
        input.nextLine(); // Consume the leftover newline character
        LocalDate newContractEndDate = LocalDate.of(year, month, 1); // Set day to 1

        System.out.println("Enter the new contract payment:");
        int newContractPayment = input.nextInt();
        input.nextLine(); // Consume the leftover newline character

        File inputFile = new File("Coach.txt");
        File tempFile = new File("Coach_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean updateContract = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ") && line.equals("Name: " + coachNameToUpdate)) {
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.equals("ID: " + coachIDToUpdate)) {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                        reader.readLine(); // Skip the current contract end date line
                        reader.readLine(); // Skip the current contract payment line
                        writer.write("Contract End Date: " + newContractEndDate);
                        writer.newLine();
                        writer.write("Contract Payment: " + newContractPayment);
                        writer.newLine();
                        // Write other details as is
                        while (!(line = reader.readLine()).contains("_______________________________________________________________________________***")) {
                            writer.write(line);
                            writer.newLine();
                        }
                        writer.write(line); // Write the separator line
                        writer.newLine();
                        updateContract = true;
                    } else {
                        writer.write(line);
                        writer.newLine();
                        writer.write(nextLine);
                        writer.newLine();
                    }
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!updateContract) {
                System.out.println("No matching entry found for the given name and ID.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while processing the file.");
            e.printStackTrace();
        }

        // Replace the original file with the temporary file
        if (!inputFile.delete()) {
            System.out.println("Could not delete original file");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temporary file");
        } else {
            System.out.println("Successfully updated the coach's contract.");
        }
    }

    public void playerAnnouncements() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the announcement for players:");
        String announcement = input.nextLine();

        LocalDateTime dateTimeOfAnnouncement = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Use try-with-resources to ensure BufferedWriter is closed properly
        try (BufferedWriter writeAnnouncement = new BufferedWriter(new FileWriter("PlayerAnnouncements.txt", true))) {
            writeAnnouncement.write("Announcement: " + announcement);
            writeAnnouncement.newLine();
            writeAnnouncement.write("Date and Time of Announcement: " + dateTimeOfAnnouncement.format(formatter));
            writeAnnouncement.newLine();
            writeAnnouncement.write("_______________________________________________________________________________***");
            writeAnnouncement.newLine();
            writeAnnouncement.newLine(); // Add an extra line for separation between entries
            System.out.println("Successfully made the announcement for players.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the announcement.");
            e.printStackTrace();
        }
    }

    public void coachAnnouncements() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the announcement for coaches:");
        String announcement = input.nextLine();

        LocalDateTime dateTimeOfAnnouncement = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Use try-with-resources to ensure BufferedWriter is closed properly
        try (BufferedWriter writeAnnouncement = new BufferedWriter(new FileWriter("CoachAnnouncements.txt", true))) {
            writeAnnouncement.write("Announcement: " + announcement);
            writeAnnouncement.newLine();
            writeAnnouncement.write("Date and Time of Announcement: " + dateTimeOfAnnouncement.format(formatter));
            writeAnnouncement.newLine();
            writeAnnouncement.write("_______________________________________________________________________________***");
            writeAnnouncement.newLine();
            writeAnnouncement.newLine(); // Add an extra line for separation between entries
            System.out.println("Successfully made the announcement for coaches.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the announcement.");
            e.printStackTrace();
        }
    }

    @Override
    public void announce() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select announcement type:");
        System.out.println("1. Player Announcement");
        System.out.println("2. Coach Announcement");
        int choice = input.nextInt();
        input.nextLine(); // Consume the leftover newline character

        switch (choice) {
            case 1:
                playerAnnouncements();
                break;
            case 2:
                coachAnnouncements();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Manager manager = new Manager("Admin", 1);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Sign in a new player");
            System.out.println("2. Sign in a new coach");
            System.out.println("3. Terminate a player's contract");
            System.out.println("4. Terminate a coach's contract");
            System.out.println("5. View player list");
            System.out.println("6. Update player jersey number");
            System.out.println("7. Make an announcement");
            System.out.println("8. Update player contract");
            System.out.println("9. Update coach contract");
            System.out.println("10. Exit");
            int choice = input.nextInt();
            input.nextLine(); // Consume the leftover newline character

            switch (choice) {
                case 1:
                    try {
                        Manager.SignInNewPlayers();
                    } catch (IOException e) {
                        System.out.println("An error occurred while signing in the new player.");
                    }
                    break;
                case 2:
                    try {
                        Manager.SignInNewCoachs();
                    } catch (IOException e) {
                        System.out.println("An error occurred while signing in the new coach.");
                    }
                    break;
                case 3:
                    try {
                        Manager.TerminateContractPlayer();
                    } catch (IOException e) {
                        System.out.println("An error occurred while terminating the player's contract.");
                    }
                    break;
                case 4:
                    try {
                        Manager.TerminateContractCoach();
                    } catch (IOException e) {
                        System.out.println("An error occurred while terminating the coach's contract.");
                    }
                    break;
                case 5:
                    manager.view();
                    break;
                case 6:
                    manager.updatePlayerJersey();
                    break;
                case 7:
                    manager.announce();
                    break;
                case 8:
                    manager.updatePlayerContract();
                    break;
                case 9:
                    manager.updateCoachContract();
                    break;
                case 10:
                    System.out.println("Exiting the program.");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
