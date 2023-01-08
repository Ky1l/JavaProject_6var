package project6;
import java.io.IOException;
import java.sql.*;

public class DBHandler {

    public static void saveAll() throws SQLException, IOException {
        var productsList = Main.getProducts();
        var conn = DriverManager.getConnection("jdbc:sqlite:src\\main\\res\\ProjectDB.db");
        var statmt = conn.createStatement();
        statmt.execute("create table Products (region varchar, country varchar, itemType varchar, " +
                "salesChannel varchar, orderPriority varchar, orderDate varchar, unitsSold int, totalProfit real);");
        for (Product product : productsList) {
            product.save(statmt);
        }
    }

    public static ResultSet execute(String sqlQuery) throws SQLException {
        var conn = DriverManager.getConnection("jdbc:sqlite:src\\main\\res\\ProjectDB.db");
        var statmt = conn.createStatement();
        return statmt.executeQuery(sqlQuery);
    }

    public static void firstTask() throws SQLException {
        var sqlQuery = "SELECT region, SUM(unitsSold) FROM Products GROUP BY region ORDER BY SUM(unitsSold)";
        var ResultSet = DBHandler.execute(sqlQuery);
        System.out.println("Task 1. Data for diagram of quantity of goods sold by regions:");
        while(ResultSet.next())
            System.out.println(ResultSet.getString("region") + " - " +
                    ResultSet.getString("SUM(unitsSold)"));
    }

    public static void secondTask() throws SQLException {
        var sqlQuery = "SELECT *, SUM(totalProfit) FROM Products WHERE (region='Europe' or region='Asia') " +
                "GROUP BY country ORDER BY SUM(totalProfit) DESC";
        var ResultSet = DBHandler.execute(sqlQuery);
        System.out.println("Task 2. The country with the highest total profit among the " +
                "regions of Europe and Asia:\n" + ResultSet.getString("country"));
    }

    public static void thirdTask() throws SQLException {
        var sqlQuery = "SELECT * FROM Products " +
                "WHERE (region='Middle East and North Africa' or region='Sub-Saharan Africa')" +
                "AND totalProfit BETWEEN 420000 AND 440000 " +
                "AND totalProfit = (SELECT MAX(totalProfit) AS M FROM Products " +
                "WHERE (region='Middle East and North Africa' or region='Sub-Saharan Africa')" +
                "AND totalProfit BETWEEN 420000 AND 440000)";
        var ResultSet = DBHandler.execute(sqlQuery);
        System.out.println("Task 3. A country with a total profit of 420 thousand to 440 thousand, " +
                "among the regions of the Middle East and North Africa and Sub-Saharan Africa, \n" +
                "with the highest total profit:\n" + ResultSet.getString("country"));
    }
}
