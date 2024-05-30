
public class Product {
    private String orderId;
    private String productId;
    private String productName;
    private String category;
    private String subCategory;
    private int quantity;
    private Double sales;

    // constructor
    public Product(String orderId, String productId, String productName, String category, String subCategory, int quantity, Double sales) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.subCategory = subCategory;
        this.quantity=quantity;
        this.sales=sales;
    }

    // getters
    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }
    public int getQuantity(){
        return quantity;
    }
    public Double getSales(){
        return sales;
    }

    // setters
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setSales(Double sales) {
        this.sales = sales;
    }
}