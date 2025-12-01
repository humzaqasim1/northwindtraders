import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("What do you want to do?\n" +
                "1) Display all products\n" +
                "2) Display all customers\n" +
                "0) Exit\n" +
                "Select an option:");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice){
            case "1":

        }
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "yearup");) {
            Statement statement = connection.createStatement();
            String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products ";

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String productId = results.getString("ProductID");
                String productName = results.getString("ProductName");
                String unitPrice = results.getString("UnitPrice");
                String unitsInStock = results.getString("UnitsInStock");
                System.out.println(
                        "Product ID :  " + productId + "\n" +
                        "Product Name :  " + productName + "\n" +
                        "Unit Price :  " + unitPrice + "\n" +
                        "Units In Stock :  " + unitsInStock
                        + "\n========================");
            }

        } catch (SQLException e){
            System.err.println("Whoops");
        }
    }
}
