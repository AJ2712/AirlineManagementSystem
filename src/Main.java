import java.util.Scanner;

public class Main {

    private static void menu(){
            System.out.println("\nMenu:");
            System.out.println("1. Passenger Details");
            System.out.println("2. Flight Details");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Display Vacancy Seat Details");
            System.out.println("6. Display Passenger Details");
            System.out.println("7. Update Passenger Information");
            System.out.println("8. Update Flight Details");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DatabaseConnect dc = new DatabaseConnect();
        actions a = new actions();
        int choice;
        System.out.println();
        System.out.println("\n\n\n=====================Welcome to Abhinandan Jain Airlines Assignment==================\n\n\n");
        do{
            menu();
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> a.insertPassengerDetails(dc);
                case 2 -> a.insertFlightDetails(dc);
                case 3 -> a.bookTickets(dc);
                case 4 -> a.cancelTickets(dc);
                case 5 -> a.displayVacanies(dc);
                case 6 -> a.displayPassengers(dc);
                case 7 -> a.updatePassengerDetails(dc);
                case 8 -> a.updateFlightDetails(dc);
                case 0 -> a.closeConnection(dc);
                default -> System.out.println("Invalid Choice!!\nPlease enter a valid choice.");
            }
        }while(choice != 0);
        System.out.println("\n\n\n=====================  Thanks for visiting !!!!  ====================\n\n\n");
    }
}
