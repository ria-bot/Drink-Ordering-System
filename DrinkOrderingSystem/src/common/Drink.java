package common;

public class Drink {
    private String brand;
    private double price;
    private int stock;

    public Drink(String brand, double price, int stock) {
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public boolean isBelowThreshold() {
        return stock < 5;
    }

    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}