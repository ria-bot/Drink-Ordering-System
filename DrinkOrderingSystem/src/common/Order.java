package common;

import java.time.LocalDateTime;

public class Order {
    private String customerName;
    private String branchName;
    private Drink drink;
    private int quantity;
    private LocalDateTime timestamp;

    public Order(String customerName, String branchName, Drink drink, int quantity) {
        this.customerName = customerName;
        this.branchName = branchName;
        this.drink = drink;
        this.quantity = quantity;
        this.timestamp = LocalDateTime.now();
    }

    public double getTotalPrice() {
        return drink.getPrice() * quantity;
    }

    public String getCustomerName() { return customerName; }
    public String getBranchName() { return branchName; }
    public Drink getDrink() { return drink; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getTimestamp() { return timestamp; }
}