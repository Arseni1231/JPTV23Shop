package org.example.shop.helpers;

import org.example.shop.models.Customer;
import org.example.shop.models.Product;
import org.example.shop.Input.Input;
import org.example.shop.repositories.CustomerRepository;
import org.example.shop.repositories.CustomerRepository;
import org.example.shop.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerHelperImplements implements CustomerHelper {
    private final CustomerRepository customerRepository;
    private final Input input;

    public CustomerHelperImplements(Input input, CustomerRepository customerRepository) {
        this.input = input;
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> create() {
        try {
            Customer customer = new Customer();
            System.out.print("Имя: ");
            customer.setFirstname(input.nextLine());
            System.out.print("Фамилия: ");
            customer.setLastname(input.nextLine());
            System.out.print("Баланс: ");
            customer.setBalance(input.nextInt());
            return Optional.of(customer);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        try {
            customer = changeFirstname(customer);
            customer = changeLastname(customer);
            customer = changeBalance(customer);
            return Optional.of(customer);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Customer changeBalance(Customer customer) {
        System.out.println("Баланс: " + customer.getBalance());
        System.out.print("Изменить (y/n): ");
        if ("y".equals(input.nextLine())) {
            System.out.print("Новый баланс: ");
            customer.setBalance(input.nextInt());
        }
        return customer;
    }

    private Customer changeLastname(Customer customer) {
        System.out.println("Фамилия: " + customer.getLastname());
        System.out.print("Изменить (y/n): ");
        if ("y".equals(input.nextLine())) {
            System.out.print("Новая фамилия: ");
            customer.setLastname(input.nextLine());
        }
        return customer;
    }

    private Customer changeFirstname(Customer customer) {
        System.out.println("Имя: " + customer.getFirstname());
        System.out.print("Изменить (y/n): ");
        if ("y".equals(input.nextLine())) {
            System.out.print("Новое имя: ");
            customer.setFirstname(input.nextLine());
        }
        return customer;
    }

    @Override
    public Optional<Long> getIdModifyCustomer(List<Customer> customers, boolean enabled) {
        this.printList(customers, true);
        System.out.print("Выберите клиента для изменения: ");
        Long id = input.nextLong();
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(Customer::getId);
    }

    @Override
    public List<Long> listIdPurchasedProducts(List<Product> products, boolean enable) {
        System.out.print("Сколько товаров купил клиент: ");
        int count = input.nextInt();
        System.out.println("\nДоступные товары:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %s (Цена: %.2f, Остаток: %d)%n",
                    i + 1, p.getName(), p.getPrice(), p.getQuantity());
        }
        List<Long> productIds = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.printf("Выберите товар %d из %d: ", i + 1, count);
            productIds.add(input.nextLong());
        }
        return productIds;
    }

    @Override
    public boolean printList(List<Customer> customers, boolean enableAll) {
        if (customers.isEmpty()) {
            return false;
        }
        for (Customer customer : customers) {
            System.out.printf("%d. %s %s, Баланс: %.2f%n",
                    customer.getId(), customer.getFirstname(), customer.getLastname(), customer.getBalance());
        }
        return true;
    }
}
