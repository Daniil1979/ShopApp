import model.*;
import repository.InMemoryProductRepository;
import repository.InMemoryOrderRepository;
import service.OrderService;
import service.RecommendationService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InMemoryProductRepository productRepo = new InMemoryProductRepository();
    private static final OrderService orderService = new OrderService(new InMemoryOrderRepository());
    private static final RecommendationService recommendationService = new RecommendationService();
    private static final User currentUser = new User("Пользователь");
    private static final Cart cart = new Cart();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в ShopApp!");

        while (true) {
            showMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> addToCart();
                case 3 -> viewCart();
                case 4 -> checkout();
                case 5 -> viewOrders();
                case 6 -> rateProduct();
                case 7 -> recommend();
                case 8 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Просмотреть все товары");
        System.out.println("2. Добавить товар в корзину");
        System.out.println("3. Просмотреть корзину");
        System.out.println("4. Оформить заказ");
        System.out.println("5. Просмотреть историю заказов");
        System.out.println("6. Оценить товар");
        System.out.println("7. Получить рекомендации");
        System.out.println("8. Выйти");
        System.out.print("Выберите опцию: ");
    }

    private static void viewProducts() {
        List<Product> products = productRepo.findAll();
        System.out.println("\nДоступные товары:");
        products.forEach(System.out::println);
    }

    private static void addToCart() {
        viewProducts();
        System.out.print("Введите название товара: ");
        String name = scanner.nextLine();
        List<Product> found = productRepo.findByNameContaining(name);
        if (!found.isEmpty()) {
            cart.addItem(found.get(0));
            System.out.println("Товар добавлен в корзину.");
        } else {
            System.out.println("Товар не найден.");
        }
    }

    private static void viewCart() {
        System.out.println(cart);
        if (!cart.isEmpty()) {
            System.out.println("Товары:");
            cart.getItems().forEach(System.out::println);
        }
    }

    private static void checkout() {
        try {
            Order order = orderService.createOrder(currentUser, cart);
            System.out.println("Заказ оформлен: " + order);
            cart.getItems().clear();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void viewOrders() {
        List<Order> orders = orderService.getOrdersByUser(currentUser);
        System.out.println("\nИстория заказов:");
        if (orders.isEmpty()) {
            System.out.println("Заказов нет.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    private static void rateProduct() {
        viewProducts();
        System.out.print("Введите название товара для оценки: ");
        String name = scanner.nextLine();
        List<Product> found = productRepo.findByNameContaining(name);
        if (!found.isEmpty()) {
            System.out.print("Оценка (1-5): ");
            int rating = getIntInput();
            service.ValidationService.validateRange(rating, 1, 5, "Оценка");
            found.get(0).setRating(rating);
            System.out.println("Товар оценён на " + rating + " звёзд.");
        } else {
            System.out.println("Товар не найден.");
        }
    }

    private static void recommend() {
        List<Product> recommendations = recommendationService.getRecommendations(currentUser);
        System.out.println("\nРекомендуем:");
        recommendations.forEach(System.out::println);
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Введите число: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}