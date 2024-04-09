import java.util.Scanner;
public class actions {
    public void insertPassengerDetails(DatabaseConnect dc) {

        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.println("Insert Passengers Details");
        // Aadhar input
        System.out.print("Enter Aadhar ID :");
        long aadhar = sc.nextLong();
        // first name input
        System.out.print("Enter First Name :");
        sc.nextLine();
        String firstName = sc.nextLine();
        // last name
        System.out.print("Enter Last Name :");
        String lastName = sc.nextLine();
        // date of birth
        System.out.print("Enter Passenger Date of Birth in yyyy-mm-dd format only:");
        String dob = sc.nextLine();
        // Mobile Number
        System.out.print("Enter Mobile Number :");
        long mobileNumber = sc.nextLong();
        // gender
        System.out.print("Enter Passenger Gender(M/F):");
        sc.nextLine();
        String gender = sc.nextLine();

        Passenger ps = new Passenger(aadhar, firstName, lastName, dob, gender,mobileNumber,0);
        dc.insertPassenger(ps);

    }

    public void insertFlightDetails(DatabaseConnect dc) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------------------------");
        System.out.println("Enter the details of the FLight");

        System.out.print("Enter Flight ID :");
        int id = sc.nextInt();
        System.out.print("Enter Flight Name :");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter Available Seats :");
        int seats = sc.nextInt();

        flight f = new flight(id, name, seats);
        dc.insertFlight(f);
    }

    public void bookTickets(DatabaseConnect dc) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Passenger Aadhar :");
        long aadhar = sc.nextLong();
        System.out.print("Enter Flight ID :");
        int flightId = sc.nextInt();

        dc.booking(aadhar, flightId);
    }

    public void cancelTickets(DatabaseConnect dc) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Passenger Aadhar to cancel Current Flight :");
        long aadhar = sc.nextLong();
        dc.cancellation(aadhar);
    }

    public void displayVacanies(DatabaseConnect dc) {
        dc.vacancies();
        System.out.println("------------------------------------------------------");
    }
    public void displayPassengers(DatabaseConnect dc){
        dc.passengers();
        System.out.println("------------------------------------------------------");
    }

    public void updatePassengerDetails(DatabaseConnect dc) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Aadhar number of the passenger: ");
        long aadhar = sc.nextLong();
//        scanner.nextLine();
        System.out.println("Select the column to update:");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Sex");
        System.out.println("4. Date of Birth");
        int choice = sc.nextInt();
        sc.nextLine();
        String columnName = "";
        switch (choice) {
            case 1:
                columnName = "first_name";
                break;
            case 2:
                columnName = "last_name";
            case 3:
                columnName = "sex";
                break;
            case 4:
                columnName = "dateOfBirth";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("Enter the new value for " + columnName + ": ");
        String newValue = sc.nextLine();

        dc.updatePassenger(aadhar, columnName, newValue);

    }

    public void updateFlightDetails(DatabaseConnect dm) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the flight ID: ");
        int flightId = sc.nextInt();
        sc.nextLine();

        System.out.println("Select the column to update:");
        System.out.println("1. Flight Name");
        System.out.println("2. Seats");
        int choice = sc.nextInt();
        sc.nextLine();

        String columnName = "";
        switch (choice) {
            case 1:
                columnName = "flightname";
                break;
            case 2:
                columnName = "seats";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("Enter the new value for " + columnName + ": ");
        String newValue = sc.nextLine();

        dm.updateFlight(flightId, columnName, newValue);
        System.out.println("------------------------------------------------------");
    }

    public void closeConnection(DatabaseConnect dm) {
        dm.close();
    }
}
