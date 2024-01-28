import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SavePlaceData {

    public static void main(String[] args) {
        // JDBC URL, username, and password of PostgreSQL server
        String url = "jdbc:postgresql://your_database_host:your_database_port/your_database_name";
        String user = "your_username";
        String password = "your_password";

        // SQL query to insert data into the "place" table
        String insertQuery = "INSERT INTO place (stateid, placename, placedetails) VALUES (?, ?, ?)";

        // Input data from the terminal
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter state ID (2 characters): ");
        String stateId = scanner.nextLine();

        System.out.print("Enter place name: ");
        String placeName = scanner.nextLine();

        System.out.print("Enter place details: ");
        String placeDetails = scanner.nextLine();

        // Close the scanner
        scanner.close();

        try {
            // Load the JDBC driver (assuming the JAR file is in the same directory as the
            // code)
            Class.forName("org.postgresql.Driver");

            // Establish a connection
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                // Create a PreparedStatement with the insert query
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    // Set the input data to the PreparedStatement
                    preparedStatement.setString(1, stateId);
                    preparedStatement.setString(2, placeName);
                    preparedStatement.setString(3, placeDetails);

                    // Execute the insert query
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully!");
                    } else {
                        System.out.println("Failed to insert data.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
