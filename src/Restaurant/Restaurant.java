package Restaurant;

import Restaurant.Menu.Dish;
import Restaurant.Menu.Drink;

import java.time.LocalTime;
import java.util.List;

class Restaurant {
    private String city;
    private List<Dish> dishes;
    private List<Drink> drinks;
    private Dish chefSpecial;

    public Restaurant(String city, List<Dish> dishes, List<Drink> drinks, Dish chefSpecial) {
        this.city = city;

        this.dishes = dishes.stream()
                .map(dish -> {
                    Dish copiedDish = new Dish(dish.getName(), dish.isVegetarian, dish.calories, dish.type, dish.getPrice());
                    if (city.equals("Москва")) {
                        copiedDish.setPrice(dish.getPrice() * 1.1);
                    }
                    return copiedDish;
                })
                .toList();

        this.drinks = drinks.stream()
                .map(drink -> {
                    Drink copiedDrink = new Drink(drink.getName(), drink.calories, drink.isAlcoholic, drink.getPrice());
                    if (city.equals("Москва")) {
                        copiedDrink.setPrice(drink.getPrice() * 1.1);
                    }
                    return copiedDrink;
                })
                .toList();

        this.chefSpecial = chefSpecial;
    }

    public List<Dish> getAvailableDishes(LocalTime time) {
        if (time.isAfter(LocalTime.of(7, 0)) && time.isBefore(LocalTime.of(11, 0))) {
            return dishes.stream().filter(dish -> dish.getName().contains("Завтрак")).toList();
        } else if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(15, 0))) {
            return dishes.stream().filter(dish -> dish.getName().contains("Ланч")).toList();
        } else {
            return dishes;
        }
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public Dish getChefSpecial() {
        return chefSpecial;
    }

    public double applyDiscount(double totalPrice, LocalTime time) {
        if (time.isAfter(LocalTime.of(15, 0)) && time.isBefore(LocalTime.of(18, 0))) {
            return totalPrice * 0.8;
        }
        return totalPrice;
    }

    public String getCity() {
        return city;
    }
}