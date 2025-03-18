package org.example.shop.Input;


import org.example.shop.models.Customer;
import org.example.shop.models.Product;
import org.example.shop.services.CustomerService;
import org.example.shop.services.ProductService;
import org.example.shop.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class Input implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PurchaseService purchaseService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Edit Product");
            System.out.println("4. Add Customer");
            System.out.println("5. List Customers");
            System.out.println("6. Edit Customer");
            System.out.println("7. Buy Product");
            System.out.println("8. Get Income");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    editProduct(scanner);
                    break;
                case 4:
                    addCustomer(scanner);
                    break;
                case 5:
                    listCustomers();
                    break;
                case 6:
                    editCustomer(scanner);
                    break;
                case 7:
                    buyProduct(scanner);
                    break;
                case 8:
                    getIncome(scanner);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        productService.saveProduct(product);
        System.out.println("Product added successfully!");
    }

    private void listProducts() {
        System.out.println("Products:");
        productService.getAllProducts().forEach(p -> System.out.println(p.getId() + ": " + p.getName() + " - $" + p.getPrice() + " (Qty: " + p.getQuantity() + ")"));
    }

    private void editProduct(Scanner scanner) {
        System.out.print("Enter product ID to edit: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        productService.updateProduct(id, product);
        System.out.println("Product updated successfully!");
    }

    private void addCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Customer customer = new Customer();
        customer.setName(name);
        customer.setBalance(balance);

        customerService.saveCustomer(customer);
        System.out.println("Customer added successfully!");
    }

    private void listCustomers() {
        System.out.println("Customers:");
        customerService.getAllCustomers().forEach(c -> System.out.println(c.getId() + ": " + c.getName() + " - Balance: $" + c.getBalance()));
    }

    private void editCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to edit: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new customer balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Customer customer = new Customer();
        customer.setName(name);
        customer.setBalance(balance);

        customerService.updateCustomer(id, customer);
        System.out.println("Customer updated successfully!");
    }

    private void buyProduct(Scanner scanner) {
        System.out.print("Enter product ID: ");
        Long productId = scanner.nextLong();
        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        scanner.nextLine(); // consume newline

        try {
            purchaseService.buyProduct(productId, customerId);
            System.out.println("Purchase successful!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getIncome(Scanner scanner) {
        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();

        LocalDateTime start = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE).atTime(23, 59, 59);

        double income = purchaseService.getIncome(start, end);
        System.out.println("Income from " + startDateStr + " to " + endDateStr + ": $" + income);
    }
}
