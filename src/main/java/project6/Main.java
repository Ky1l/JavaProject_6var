package project6;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        DBHandler.firstTask();
        DBHandler.secondTask();
        DBHandler.thirdTask();
    }

    public static ArrayList<Product> getProducts() throws IOException {
        var products = new ArrayList<Product>();
        try (var br = new BufferedReader(new FileReader("src\\main\\res\\Продажа продуктов в мире.csv"))) {
            var line = br.readLine();
            var head = br.readLine();
            while (line != null) {
                products.add(new Product(line.split(",")));
                line = br.readLine();
            }
        }
        return products;
    }
}
