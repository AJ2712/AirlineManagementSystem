import java.sql.*;

public class DatabaseConnect{
    static final String url = "jdbc:mysql://localhost:3306/airlines";
    static final String uname = "root";
    static final String pwd = "Abhi@12345";
    private Connection connection;
    public DatabaseConnect(){
        try {
            connection = DriverManager.getConnection(url,uname,pwd);
            System.out.println("Database Connection Established !!");
        } catch (SQLException e) {
            System.out.println("Can not connect to the database");
            e.printStackTrace();
        }
    }
    public void insertPassenger(Passenger passenger) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO passenger VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, passenger.getAadhar());
            statement.setString(2, passenger.getFirstName());
            statement.setString(3, passenger.getLastName());
            statement.setString(4, passenger.getDob());
            statement.setLong(5, passenger.getMobileNumber());
            statement.setString(6, passenger.getSex());
            statement.setInt(7, passenger.getFlightId());
            statement.executeUpdate();
            System.out.println("Passenger Added Successfully!");
            System.out.println("--------------------------------------------");
            System.out.println();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Exception occurs in Passenger Insertion!");
            e.printStackTrace();
        }
    }

    public void insertFlight(flight flight) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO flight values (?, ?, ?)");
            statement.setInt(1, flight.getFlightid());
            statement.setString(2, flight.getName());
            statement.setInt(3, flight.getSeats());
            statement.executeUpdate();
            System.out.println("Flight Added Successfully!");
            System.out.println("--------------------------------------------");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Exception occurs in Flight Insertion!");
            e.printStackTrace();
        }
    }

    public void booking(long passengerId, int flightId) {
        try {
            String checkSeatsQuery = "SELECT seats FROM flight WHERE flight_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(checkSeatsQuery);
            preparedStatement.setInt(1, flightId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int availableSeats = resultSet.getInt("seats");
                if (availableSeats > 0) {
                    // Book the ticket
                    String bookTicketQuery = "UPDATE passenger set flight_id = ? where aadhar = ?";
                    preparedStatement = connection.prepareStatement(bookTicketQuery);
                    preparedStatement.setInt(1, flightId);
                    preparedStatement.setLong(2, passengerId);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        // Update available seats
                        String updateSeatsQuery = "UPDATE flight SET seats = ? WHERE flight_id = ?";
                        preparedStatement = connection.prepareStatement(updateSeatsQuery);
                        preparedStatement.setInt(1, availableSeats - 1);
                        preparedStatement.setInt(2, flightId);
                        int updatedRows = preparedStatement.executeUpdate();
                        if (updatedRows > 0) {
                            System.out.println("Updated!!!!");
                        }
                        System.out.println("Voila!! Tickets Booked ");
                        System.out.println("-----------------------------------------------------");
                    }
                } else {
                    System.out.println("Sorry, no available seats for this flight.");
                }
            } else {
                System.out.println("Invalid flight ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancellation(long passengerId) {
        PreparedStatement preparedStatement;
        try {
            String getFlightIdQuery = "SELECT flight_id FROM passenger WHERE aadhar = ?";
            preparedStatement = connection.prepareStatement(getFlightIdQuery);
            preparedStatement.setLong(1, passengerId);
            int flightId = 0;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    flightId = resultSet.getInt("flight_id");
                }
            }

            String cancelTicketQuery = "UPDATE passenger SET flight_id = 0 WHERE aadhar = ?";
            preparedStatement = connection.prepareStatement(cancelTicketQuery);
            preparedStatement.setLong(1, passengerId);
            int rowsAffectedPassenger = preparedStatement.executeUpdate();

            String incrementSeatsQuery = "UPDATE flight SET seats = seats + 1 WHERE flight_id = ?";
            preparedStatement = connection.prepareStatement(incrementSeatsQuery);
            preparedStatement.setInt(1, flightId);
            int rowsAffectedFlight = preparedStatement.executeUpdate();

            if (rowsAffectedPassenger > 0 && rowsAffectedFlight > 0) {
                System.out.println("Ticket Canceled Successfully!!!");
                System.out.println("------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong !! (-_-)");
        }
    }

    public void vacancies() {
        PreparedStatement preparedStatement ;
        ResultSet resultSet ;
        try {
            String query = "SELECT flight_id, name, seats FROM flight";
            preparedStatement = connection.prepareStatement(query);
            resultSet= preparedStatement.executeQuery();

            // Print table header
            System.out.println("------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s%n", "Flight ID", "Flight Name", "Vacant Seats");
            System.out.println("------------------------------------------------------");
            // Print table rows
            while (resultSet.next()) {
                int flightId = resultSet.getInt("flight_id");
                String flightName = resultSet.getString("name");
                int vacantSeats = resultSet.getInt("seats");
                System.out.printf("%-10d %-20s %-10d%n", flightId, flightName, vacantSeats);
            }
            System.out.println("------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void passengers(){
        PreparedStatement preparedStatement ;
        ResultSet resultSet ;
        try {
            String query = "SELECT * FROM passenger";
            preparedStatement = connection.prepareStatement(query);
            resultSet= preparedStatement.executeQuery();

            // Print table header
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.printf("%-13s %-21s %-11s %-15s %-11s %-10s %-11s %n", "Passenger Id", "First Name", "Last Name", "Date Of Birth", "Mobile Number", "Gender","Flight Id");
            System.out.println("----------------------------------------------------------------------------------------------------------");
            // Print table rows
            while (resultSet.next()) {
                long Id = resultSet.getLong("aadhar");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String dob = resultSet.getString("DateOfBirth");
                long mobileNumber = resultSet.getLong("MobileNumber");
                String gender = resultSet.getString("Sex");
                int flightId = resultSet.getInt("flight_id");

                System.out.printf("%-13d %-21s %-11s %-15s %-15d %-10s %-11d %n",Id,firstName,lastName,dob,mobileNumber,gender,flightId);
            }
            System.out.println("----------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassenger(long aadhar, String columnName, String newValue) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            String updateQuery = "UPDATE passenger SET " + columnName + " = ? WHERE aadhar = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newValue);
            preparedStatement.setLong(2, aadhar);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Passenger Details Updated Successfully!");
                System.out.println("---------------------------------------------------------------------------------------------");
            } else {
                System.out.println("No rows were updated. Please check the provided Aadhar number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateFlight(int flightId, String columnName, String newValue) {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {

            String updateQuery = "UPDATE flight SET " + columnName + " = ? WHERE flight_id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, flightId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Flights details Updated Successfully!");
            } else {
                System.out.println("No rows were updated. Please check the provided flight ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            connection.close();
        }
        catch(Exception e) {
            System.out.println("Failed to close connection!");
        }
    }
}
