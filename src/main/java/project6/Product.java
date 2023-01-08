package project6;
import java.sql.Statement;
import java.sql.SQLException;

public class Product {
    private final String region;
    private final String country;
    private final String itemType;
    private final String salesChannel;
    private final OrderPriority orderPriority;
    private final String orderDate;
    private final long unitsSold;
    private final float totalProfit;

    public Product(String[] arr) {
        this.region = arr[0];
        this.country = arr[1];
        this.itemType = arr[2];
        this.salesChannel = arr[3];
        this.orderPriority = OrderPriority.valueOf(arr[4]);
        this.orderDate = arr[5];
        this.unitsSold = Long.parseLong(arr[6]);
        this.totalProfit = Float.parseFloat(arr[7]);
    }

    @Override
    public String toString() {
        return String.format("Region: %s | Country: %s | Item Type: %s | Sales Channel: %s |Order Priority: %s " +
                        "| Order_Date: %s | Units Sold: %s | Total Profit: %s",
                this.region, this.country, this.itemType, this.salesChannel, this.orderPriority, this.orderDate,
                this.unitsSold, this.totalProfit);
    }

    public void save(Statement st) throws SQLException {
        var sql = String.format("insert into Products(region, country, itemType, salesChannel, orderPriority, "+
                        "orderDate,unitsSold,totalProfit) values ('%s','%s','%s','%s','%s','%s', %s, %s)", region,
                country, itemType, salesChannel, orderPriority, orderDate, unitsSold, totalProfit);
        System.out.println(sql);
        st.executeUpdate(sql);
    }
}
