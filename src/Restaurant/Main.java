package Restaurant;

import Restaurant.Menu.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Dish> dishes = Arrays.asList(
                new Dish("Завтрак Омлет", true, 300, "Другое", 250),
                new Dish("Ланч Бифштекс", false, 600, "Мясо", 450),
                new Dish("Ужин Рыба", false, 400, "Рыба", 500)
        );

        List<Drink> drinks = Arrays.asList(
                new Drink("Сок", 100, false, 100),
                new Drink("Вино", 150, true, 300)
        );

        Restaurant moscowRestaurant = new Restaurant("Москва", dishes, drinks, new Dish("Специальное блюдо Москвы", true, 500, "Другое", 800));
        Restaurant spbRestaurant = new Restaurant("Санкт-Петербург", dishes, drinks, new Dish("Специальное блюдо СПб", false, 700, "Мясо", 700));

        Map<Integer, Restaurant> restaurants = Map.of(1, moscowRestaurant, 2, spbRestaurant);

        System.out.println("Выберите ресторан: 1 - Москва, 2 - Санкт-Петербург");
        int choice = scanner.nextInt();
        Restaurant selectedRestaurant = restaurants.get(choice);

        System.out.println("Меню ресторана в " + selectedRestaurant.getCity() + ":");
        LocalTime currentTime = LocalTime.now();
        selectedRestaurant.getAvailableDishes(currentTime).forEach(System.out::println);
        selectedRestaurant.getDrinks().forEach(System.out::println);
        System.out.println("Специальное блюдо шефа: " + selectedRestaurant.getChefSpecial());

        System.out.println("Введите название блюда для заказа:");
        scanner.nextLine();
        String dishName = scanner.nextLine();

        double totalPrice = selectedRestaurant.getAvailableDishes(currentTime).stream()
                .filter(dish -> dish.getName().equalsIgnoreCase(dishName))
                .mapToDouble(Dish::getPrice)
                .sum();

        totalPrice = selectedRestaurant.applyDiscount(totalPrice, currentTime);

        System.out.println("Итоговая сумма: " + String.format("%.2f", totalPrice) + " руб.");
    }
}
