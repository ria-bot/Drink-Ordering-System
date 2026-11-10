package common;

import java.util.*;

public class Branch {
    private String name;
    private List<Drink> drinks = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private double totalSales = 0.0;

    public Branch(String name) {
        this.name = name;
    }

    public void processOrder(Order order) {
        order.getDrink().reduceStock(order.getQuantity());
        totalSales += order.getTotalPrice();
        orders.add(order);
    }

    public void checkLowStock() {
        for (Drink drink : drinks) {
            if (drink.isBelowThreshold()) {
                System.out.println("ALERT: " + drink.getBrand() + " is below threshold at " + name);
            }
        }
    }

    public String getName() { return name; }
    public double getTotalSales() { return totalSales; }
    public List<Order> getOrders() { return orders; }
    public void addDrink(Drink drink) { drinks.add(drink); }
    public List<Drink> getDrinks() { return drinks; }
}