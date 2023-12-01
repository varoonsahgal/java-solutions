import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StoreApp {

    private ArrayList<Product> inventory;

    public static void main(String[] args) {
        StoreApp storeApp = new StoreApp();
       //modify the path to match your products.txt file:
        storeApp.loadInventoryFromFile("/Users/varoonsahgal/Downloads/untitled/src/products.txt"); // Load inventory from a file

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1. List all products");
            System.out.println("2. Lookup a product by its id");
            System.out.println("3. Find all products within a price range");
            System.out.println("4. Add a new product");
            System.out.println("5. Quit the application");

            System.out.print("Enter command: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    storeApp.listAllProducts();
                    break;
                case 2:
                    storeApp.lookupProductById(scanner);
                    break;
                case 3:
                    storeApp.findProductsInPriceRange(scanner);
                    break;
                case 4:
                    storeApp.addNewProduct(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid command.");
            }
        }
    }

    public void loadInventoryFromFile(String filename) {
        inventory = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    float price = Float.parseFloat(parts[2].trim());
                    inventory.add(new Product(id, name, price));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listAllProducts() {
        sortInventoryByName();
        for (Product p : inventory) {
            System.out.printf("id: %d %s - Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }

    public void lookupProductById(Scanner scanner) {
        System.out.print("Enter product id: ");
        int idToLookup = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (Product p : inventory) {
            if (p.getId() == idToLookup) {
                System.out.printf("id: %d %s - Price: $%.2f%n",
                        p.getId(), p.getName(), p.getPrice());
                return;
            }
        }
        System.out.println("Product not found with id: " + idToLookup);
    }

    public void findProductsInPriceRange(Scanner scanner) {
        System.out.print("Enter minimum price: ");
        float minPrice = scanner.nextFloat();
        System.out.print("Enter maximum price: ");
        float maxPrice = scanner.nextFloat();
        scanner.nextLine(); // Consume the newline

        System.out.println("Products within the price range:");
        for (Product p : inventory) {
            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                System.out.printf("id: %d %s - Price: $%.2f%n",
                        p.getId(), p.getName(), p.getPrice());
            }
        }
    }

    public void addNewProduct(Scanner scanner) {
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        float price = scanner.nextFloat();
        scanner.nextLine(); // Consume the newline

        Product newProduct = new Product(id, name, price);
        inventory.add(newProduct);
        System.out.println("New product added: " + newProduct.getName());
    }

    private void sortInventoryByName() {
        Collections.sort(inventory, Comparator.comparing(Product::getName));
    }
}
