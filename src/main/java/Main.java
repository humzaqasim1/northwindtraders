import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");
        Connection connection = dataSource.getConnection();

        System.out.println("What do you want to do?\n" +
                "1) Display all products\n" +
                "2) Display all customers\n" +
                "0) Exit\n" +
                "Select an option:");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                queryProducts(dataSource);
                break;
            case "2":
                queryCustomers(dataSource);
                break;
            default:
                System.out.println("Nah");
        }
    }

    public static void queryProducts(BasicDataSource dataSource) {
        String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM products ";
        try(Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet results = preparedStatement.executeQuery(query);

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

    public static void queryCustomers(BasicDataSource dataSource) {
        String query = "SELECT ContactName, CompanyName, City, Country, Phone FROM Customers ";
        try(Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet results = preparedStatement.executeQuery(query);

            while (results.next()) {
                String contactName = results.getString("ContactName");
                String companyName = results.getString("CompanyName");
                String city = results.getString("City");
                String country = results.getString("Country");
                String phone = results.getString("Phone");
                System.out.println(
                        "Contact Name :  " + contactName + "\n" +
                                "Company Name :  " + companyName + "\n" +
                                "City :  " + city + "\n" +
                                "Country :  " + country + "\n" +
                                "Phone :  " + phone +
                                "\n========================");
            }

        } catch (SQLException e){
            System.err.println("Whoops");
        }
    }
}
