package Phone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Phone> phones = generateRandomPhones(30);

        System.out.println("Сортировка по цене:");
        phones.sort(Comparator.comparingInt(Phone::getPrice));
        phones.forEach(System.out::println);

        System.out.println("\nСортировка по весу:");
        phones.sort(Comparator.comparingInt(Phone::getWeight));
        phones.forEach(System.out::println);
    }

    public static List<Phone> generateRandomPhones(int count) {
        List<Phone> phones = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            phones.add(Phone.generateRandomPhone());
        }
        return phones;
    }
}



