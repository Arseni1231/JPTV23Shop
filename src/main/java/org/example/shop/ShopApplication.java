package org.example.shop;

import org.example.shop.Input.Input;
import org.example.shop.services.CustomerServiceImplements;
import org.example.shop.services.ProductServiceImplements;
import org.example.shop.services.PurchaseServiceImplements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class ShopApplication implements CommandLineRunner {
    private final CustomerServiceImplements customerServiceImplements;
    private final ProductServiceImplements productServiceImplements;
    private final PurchaseServiceImplements purchaseServiceImplements;
    private final Input input;

    public ShopApplication(Input input, CustomerServiceImplements customerServiceImplements,
                           ProductServiceImplements productServiceImplements, PurchaseServiceImplements purchaseServiceImplements) {
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
        while (repeat) {
            System.out.println("\n--- МАГАЗИН ---");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Список продуктов");
            System.out.println("3. Изменить продукт");
            System.out.println("4. Добавить покупателя");
            System.out.println("5. Список покупателей");
            System.out.println("6. Изменить покупателя");
            System.out.println("7. Купить продукт");
            System.out.println("8. Получить доход");
            System.out.println("9. Выход");
            System.out.print("Выберите вариант: ");

            try {
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println(productServiceImplements.add() ? "Продукт добавлен" : "Продукт не добавлен");
                        break;
                    case 2:
                        if (!productServiceImplements.print()) {
                            System.out.println("Нету доступных продуктов");
                        }
                        break;
                    case 3:
                        if (!productServiceImplements.edit()) {
                            System.out.println("Продукт не изменен");
                        }
                        break;
                    case 4:
                        System.out.println(customerServiceImplements.add() ? "Покупатель добавлен" : "Покупатель не добавлен");
                        break;
                    case 5:
                        if (!customerServiceImplements.print()) {
                            System.out.println("Нету покупателей");
                        }
                        break;
                    case 6:
                        if (!customerServiceImplements.edit()) {
                            System.out.println("Покупатель не изменен");
                        }
                        break;
                    case 7:
                        System.out.print("Введите ID продукта: ");
                        Long productId = input.nextLong();
                        System.out.print("Введите ID клиента: ");
                        Long customerId = input.nextLong();

                        if (purchaseServiceImplements.buyProduct(productId, customerId) != null) {
                            System.out.println("Покупка успешна");
                        } else {
                            System.out.println("Ошибка при покупке");
                        }

                    case 8:
                        System.out.println("Вышло по итогу: " + purchaseServiceImplements.getIncome());
                        break;
                    case 9:
                        System.out.println("Выход с программы...");
                        repeat = false;
                        break;
                    default:
                        System.out.println("Неверно-выбранная опция. Пожалуйста, повторите попытку");
                }
            } catch (Exception e) {
                System.out.println("Неправильный ввод. Пожалуйста, введите число");
                input.nextLine(); // Очистка ввода
            }
        }
    }
}

