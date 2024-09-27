package PersonSort;

public class Main
{
    public static void main(String[] args)
    {
        Array array = new Array(5);

        array.insert(new Person("Ольга", "Бессонова", 50));
        array.insert(new Person("Артем", "Бессонов", 18));
        array.insert(new Person("Дмитрий", "Бессонов", 30));
        array.insert(new Person("семья", "кончилась", 35));
        array.insert(new Person("что", "писать", 25));

        System.out.println("Исходный массив:");
        array.display();

        System.out.println("\nПузырьковая сортировка:");
        array.bubbleSort();
        array.display();

        System.out.println("\nСортировка выбором:");
        array.selectionSort();
        array.display();

        System.out.println("\nСортировка вставками:");
        array.insertionSort();
        array.display();
    }
}
