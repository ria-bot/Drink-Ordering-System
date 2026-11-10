package server;

import common.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class HeadquarterServer {
    private static Map<String, Branch> branches = new HashMap<>();
    private static final int PORT = 9000;

    public static void main(String[] args) {
        setupBranches();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("HQ Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setupBranches() {
        String[] branchNames = {"NAIROBI", "NAKURU", "MOMBASA", "KISUMU"};
        for (String name : branchNames) {
            Branch branch = new Branch(name);
            branch.addDrink(new Drink("CocaCola", 1.50, 10));
            branch.addDrink(new Drink("Fanta", 1.40, 8));
            branch.addDrink(new Drink("Sprite", 1.30, 6));
            branches.put(name, branch);
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                Order order = (Order) ois.readObject();
                Branch branch = branches.get(order.getBranchName());
                if (branch != null) {
                    branch.processOrder(order);
                    branch.checkLowStock();
                    System.out.println("Processed: " + order.getCustomerName() + " | " + order.getDrink().getBrand() + " x" + order.getQuantity() + " from " + branch.getName());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}