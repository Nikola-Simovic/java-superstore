import java.util.Date;

public class Order {
    private String orderId;
    private Customer customer;
    private Date orderDate;
    private Date shipDate;
    private String shipMode;
    private Double sales; 


    public Order(String orderId, Customer customer, Date orderDate, Date shipDate, String shipMode, Double sales) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.shipMode = shipMode;
        this.sales = sales;
    }

    // getters
    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public String getShipMode() {
        return shipMode;
    }

    public Double getSales() {
        return sales;
    }

    // setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public void setShipMode(String shipMode) {
        this.shipMode = shipMode;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }
}