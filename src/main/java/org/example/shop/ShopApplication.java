package org.example.shop;

import org.example.shop.Input.Input;
import org.example.shop.services.CustomerServiceImplements;
import org.example.shop.services.ProductServiceImplements;
import org.example.shop.services.PurchaseService;
import org.example.shop.services.PurchaseServiceImplements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {
    private final CustomerServiceImplements customerServiceImplements;
    private final ProductServiceImplements productServiceImplements;
    private final PurchaseServiceImplements purchaseServiceImplements;
    private Input input;

    public ShopApplication(CustomerServiceImplements customerServiceImplements, ProductServiceImplements productServiceImplements, PurchaseService purchaseService, PurchaseServiceImplements purchaseServiceImplements) {
        this.input = input;
        this.customerServiceImplements = customerServiceImplements;
        this.productServiceImplements = productServiceImplements;

        this.purchaseServiceImplements = purchaseServiceImplements;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        boolean repeat = true;
        do {
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
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    if(productServiceImplements.add()){
                        System.out.println("Product added");
                    }else{
                        System.out.println("Product not added");
                    }
                    break;
                case 2:
                    if(!productServiceImplements.print()){
                        System.out.println("Product not printed");
                    }
                    break;
                case 3:
                    if(!productServiceImplements.edit()){
                        System.out.println("Product not edited");
                    }
                    break;
                case 4:
                    if(customerServiceImplements.add()){
                        System.out.println("Customer added");
                    }else{
                        System.out.println("Customer is not added");
                    }
                    break;
                case 5:
                    if(!customerServiceImplements.print()){
                        System.out.println("Customer not printed");
                    }
                    break;
                    case 6:
                        if(!customerServiceImplements.edit()){
                            System.out.println("Customer not edited");
                        }
                            break;
                        case 7:
                            if()
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
