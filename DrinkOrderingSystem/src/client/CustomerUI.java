package client;

import common.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class CustomerUI {
    private static final String[] DRINKS = {"CocaCola", "Fanta", "Sprite"};
    private static final double[] PRICES = {1.50, 1.40, 1.30};

    public static void main(String[] args) {
        JFrame frame = new JFrame("Drink Order - Choose Branch");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JTextField nameField = new JTextField();
        JComboBox<String> drinkBox = new JComboBox<>(DRINKS);
        JTextField quantityField = new JTextField();
        JComboBox<String> branchBox = new JComboBox<>(new String[]{"NAIROBI", "NAKURU", "MOMBASA", "KISUMU"});
        JButton submitButton = new JButton("Place Order");

        panel.add(new JLabel("Your Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Select Drink:"));
        panel.add(drinkBox);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Select Branch:"));
        panel.add(branchBox);
        panel.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String drinkName = (String) drinkBox.getSelectedItem();
            String branchName = (String) branchBox.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());

            double price = PRICES[drinkBox.getSelectedIndex()];
            Drink drink = new Drink(drinkName, price, 10);
            Order order = new Order(name, branchName, drink, quantity);

            try (Socket socket = new Socket("localhost", 9000);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
                oos.writeObject(order);
                JOptionPane.showMessageDialog(frame, "Order sent successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to send order.");
            }
        });

        frame.add(panel);
        frame.setSize(300, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}