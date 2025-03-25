package org.example.shop.helpers;

import org.example.shop.Input.Input;
import org.example.shop.models.Product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductHelperImplements implements ProductHelper {

    private final Input input;

    public ProductHelperImplements(Input input) {
        this.input = input;
    }

    @Override
    public Optional<Product> create() {
        try {
            Product product = new Product();
            System.out.print("Название товара: ");
            product.setName(input.nextLine());
            System.out.print("Цена товара: ");
            product.setPrice(input.nextDouble());
            System.out.print("Количество товара: ");
            product.setQuantity(input.nextInt());
            return Optional.of(product);
        } catch (Exception e) {
            System.out.println("Ошибка при создании товара: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> update(Product product) {
        try {
            System.out.print("Новое название товара (текущее: " + product.getName() + "): ");
            product.setName(input.nextLine());
            System.out.print("Новая цена (текущая: " + product.getPrice() + "): ");
            product.setPrice(input.nextDouble());
            System.out.print("Новое количество (текущее: " + product.getQuantity() + "): ");
            product.setQuantity(input.nextInt());
            return Optional.of(product);
        } catch (Exception e) {
            System.out.println("Ошибка при редактировании товара: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean printList(List<Product> products, boolean enableAll) {
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст.");
            return false;
        }
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %s - %.2f EUR (Остаток: %d)\n", i + 1, product.getName(), product.getPrice(), product.getQuantity());
        }
        return true;
    }

    @Override
    public Optional<Long> getIdModifyProduct(List<Product> products, boolean enabled) {
        if (products.isEmpty()) {
            System.out.println("Список товаров пуст!");
            return Optional.empty();
        }


        System.out.println("\nСписок доступных товаров:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %s (Цена: %.2f, Остаток: %d)%n",
                    i + 1, product.getName(), product.getPrice(), product.getQuantity());
        }


        System.out.print("\nВведите номер товара для редактирования: ");
        try {
            int choice = input.nextInt();
            input.nextLine();

            // Проверяем корректность выбора
            if (choice >= 1 && choice <= products.size()) {
                return Optional.of(products.get(choice - 1).getId());
            } else {
                System.out.println("Некорректный номер товара!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
            input.nextLine();
        }

        return Optional.empty();
    }

}
