
public class Customer {
    private String orderId;
    private String customerId;
    private String customerName;
    private String segment;
    private String country;
    private String city;
    private String state;
    private int postalCode;
    private String region;
    private double sales;

    public Customer(String customerId, String customerName, String orderId, String segment, String country, String city,
            String state, int postalCode, String region, double sales) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.segment = segment;
        this.country = country;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.region = region;
        this.sales = sales;
    }

    // getters
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSegment() {
        return segment;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getRegion() {
        return region;
    }

    public double getSales() {
        return sales;
    }

    // setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String customerName) {
        this.customerName = customerName;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
