/*OOP PROJECT
*PROJECT TITLE VITAL EVENT REGISTRATION
 * GROUP MEMBERS
 * AMIR ABDI  UGR/29865/14
 * AJMAI ABAS UGR/29770/14
 * MEKIYA WELIY  UGR/31558/14
 * RUTH MESFIN UGR/22454/13
 * 
 */
import java.io.*;

class VitalEvent {
    private String regId;
    private String eventType;
    private String name;
    private String gender;
    private String date;
    
    // Constructor
    public VitalEvent(String regId, String eventType, String name, String gender, String date) {
        this.regId = regId;
        this.eventType = eventType;
        this.name = name;
        this.gender = gender;
        this.date = date;
    }
    
    // Getters
    public String getRegId() {
        return this.regId;
    }
    
    public String getEventType() {
        return this.eventType;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public String getDate() {
        return this.date;
    }
}

class VitalEventRegistrationSystem {
    private static final String USERNAME = "AMIR";
    private static final String PASSWORD = "AJMEL";
    
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter username: ");
        String username = "";
        try {
            username = br.readLine();
        } catch(IOException e) {}
        System.out.print("Enter password: ");
        String password = "";
        try {
            password = br.readLine();
        } catch(IOException e) {}
        if(!authenticate(username, password)) {
            System.out.println("Invalid username or password!");
            return;
        }
        System.out.println("1. Register a vital event");
        System.out.println("2. Display a specific report");
        System.out.println("3. Search user information by registration ID");
        System.out.println("4. Exit");
        int choice;
        boolean loop = true;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(br.readLine());
            } catch(NumberFormatException | IOException e) {
                choice = 4;
            }
            switch(choice) {
                case 1:
                    System.out.println("1. Register a vital event");
                    System.out.print("Enter registration ID: ");
                    String regId = "";
                    try {
                        regId = br.readLine();
                    } catch(IOException e) {}
                    System.out.print("Enter event type (Birth/Death/Marriage/Divorce): ");
                    String eventType = "";
                    try {
                        eventType = br.readLine();
                    } catch(IOException e) {}
                    System.out.print("Enter name: ");
                    String name = "";
                    try {
                        name = br.readLine();
                    } catch(IOException e) {}
                    System.out.print("Enter gender: ");
                    String gender = "";
                    try {
                        gender = br.readLine();
                    } catch(IOException e) {}
                    System.out.print("Enter date (DD/MM/YYYY): ");
                    String date = "";
                    try {
                        date = br.readLine();
                    } catch(IOException e) {}
                    VitalEvent vitalEvent = new VitalEvent(regId, eventType, name, gender, date);
                    registerVitalEvent(vitalEvent);
                    break;
                case 2:
                    System.out.println("2. Display a specific report");
                    System.out.print("Enter event type (Birth/Death/Marriage/Divorce): ");
                    String eventType2 = "";
                    try {
                        eventType2 = br.readLine();
                    } catch(IOException e) {}
                    displaySpecificReport(eventType2);
                    break;
                case 3:
                    System.out.println("3. Search user information by registration ID");
                    System.out.print("Enter registration ID: ");
                    String regId2 = "";
                    try {
                        regId2 = br.readLine();
                    } catch(IOException e) {}
                    searchUserInformationById(regId2);
                    break;
                case 4:
                    System.out.println("4. Goodbye!");
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while(loop);
    }
    
    private static boolean authenticate(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
    
    private static void registerVitalEvent(VitalEvent vitalEvent) {
        String filename = "";
        if(vitalEvent.getEventType().equalsIgnoreCase("Birth")) {
            filename = "birth.txt";
        } else if(vitalEvent.getEventType().equalsIgnoreCase("Death")) {
            filename = "death.txt";
        } else if(vitalEvent.getEventType().equalsIgnoreCase("Marriage")) {
            filename = "marriage.txt";
        } else if(vitalEvent.getEventType().equalsIgnoreCase("Divorce")) {
            filename = "divorce.txt";
        } else {
            System.out.println("Invalid event type!");
            return;
        }
        try {
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(vitalEvent.getRegId() + "," + vitalEvent.getName() + "," + vitalEvent.getGender() + "," + vitalEvent.getDate() + "\n");
            fileWriter.close();
            System.out.println("Vital event registered successfully!");
        } catch(IOException e) {
            System.out.println("An error occurred while registering vital event!");
        }
    }
    
    private static void displaySpecificReport(String eventType) {
        String filename = "";
        if(eventType.equalsIgnoreCase("Birth")) {
            filename = "birth.txt";
        } else if(eventType.equalsIgnoreCase("Death")) {
            filename = "death.txt";
        } else if(eventType.equalsIgnoreCase("Marriage")) {
            filename = "marriage.txt";
        } else if(eventType.equalsIgnoreCase("Divorce")) {
            filename = "divorce.txt";
        } else {
            System.out.println("Invalid event type!");
            return;
        }
        try {
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("Registration ID: " + data[0]);
                System.out.println("Name: " + data[1]);
                System.out.println("Gender: " + data[2]);
                System.out.println("Date: " + data[3]);
                System.out.println("-------------------------");
            }
            br.close();
        } catch(IOException e) {
            System.out.println("An error occurred while displaying specific report!");
        }
    }
    
    private static void searchUserInformationById(String regId) {
        String[] filenames = {"birth.txt", "death.txt", "marriage.txt", "divorce.txt"};
        boolean found = false;
        for(String filename : filenames) {
            try {
                File file = new File(filename);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if(data[0].equals(regId)) {
                        found = true;
                        System.out.println("Registration ID: " + data[0]);
                        System.out.println("Name: " + data[1]);
                        System.out.println("Gender: " + data[2]);
                        System.out.println("Date: " + data[3]);
                        System.out.println("-------------------------");
                        break;
                    }
                }
                br.close();
                if(found) {
                    break;
                }
            } catch(IOException e) {
                System.out.println("An error occurred while searching user information!");
            }
        }
        if(!found) {
            System.out.println("Registration ID not found!");
        }
    }
}
