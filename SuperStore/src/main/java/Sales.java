
public class Sales {
    private String orderId;
    private Order order;
    private Product product;
    private int quantity;
    private double discount;
    private double profit;
    private Double sales;
    private String region;

    // constructor
    public Sales(String orderId, Order order, Product product, int quantity, double discount, double profit,
            Double sales, String region) {
        this.orderId = orderId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.profit = profit;
        this.sales = sales;
        this.region = region;
    }

    // getters
    public String getOrderId() {
        return orderId;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public double getProfit() {
        return profit;
    }

    public Double getSales() {
        return sales;
    }

    public String getRegion() {
        return region;
    }

    // setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
