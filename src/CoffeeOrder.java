public class CoffeeOrder {
    private String customerName;
    private String drinkType;
    private boolean isIced;
    private double basePrice;

    public CoffeeOrder(String customerName, String drinkType, boolean isIced, double basePrice) {
        this.customerName = customerName;
        this.drinkType = drinkType;
        this.isIced = isIced;
        this.basePrice = basePrice;
    }

    public String getCustomerName() { return customerName; }
    public String getDrinkType() { return drinkType; }
    public boolean isIced() { return isIced; }
    public double getBasePrice() { return basePrice; }
}