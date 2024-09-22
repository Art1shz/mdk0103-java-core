package ArrayTask;

import java.util.Random;
import java.util.Scanner;

public class ArrayTask
{
    public static void main(String[] args)
    {
        int[] array = new int[100];
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(101);
        }

        while (true)
        {
            System.out.println("\nМеню: \n 1. Вывести массив \n 2. Найти элемент в массиве (Бинарный поиск) \n 3. Удалить элемент по индексу \n 4. Вставить элемент на указанное место \n 5. Выход");
            System.out.print("Выберите действие: ");
            int selection = scanner.nextInt();

            switch (selection)
            {
                case 1:
                    printArray(array);
                    break;

                case 2:
                    System.out.print("Введите элемент для поиска: ");
                    int targetSearch = scanner.nextInt();
                    int index = binarySearch(array, targetSearch);
                    if (index >= 0)
                    {
                        System.out.println("Элемент найден на позиции: " + index);
                    }
                    else
                    {
                        System.out.println("Элемент не найден");
                    }
                    break;

                case 3:
                    System.out.print("Введите индекс элемента для удаления: ");
                    int indexToRemove = scanner.nextInt();
                    array = removeElement(array, indexToRemove);
                    System.out.println("Элемент удален. Обновленный массив:");
                    printArray(array);
                    break;

                case 4:
                    System.out.print("Введите элемент для вставки: ");
                    int element = scanner.nextInt();
                    System.out.print("Введите позицию для вставки (0 до " + array.length + "): ");
                    int position = scanner.nextInt();
                    array = insertElement(array, element, position);
                    System.out.println("Элемент вставлен. Обновленный массив:");
                    printArray(array);
                    break;

                case 5:
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    public static int binarySearch(int[] array, int target)
    {
        int left = 0;
        int right = array.length - 1;

        while (left <= right)
        {
            int mid = (left + right) / 2;

            if (array[mid] == target)
            {
                return mid;
            }
            else if (array[mid] < target)
            {
                left = mid + 1;
            }
            else
            {
                right = mid - 1;
            }
        }
        return -(left + 1);
    }

    public static int[] insertElement(int[] array, int element, int position)
    {
        if (position < 0 || position > array.length)
        {
            System.out.println("Неверная позиция для вставки.");
            return array;
        }

        int[] newArray = new int[array.length + 1];

        for (int i = 0; i < position; i++)
        {
            newArray[i] = array[i];
        }

        newArray[position] = element;

        for (int i = position; i < array.length; i++)
        {
            newArray[i + 1] = array[i];
        }

        return newArray;
    }

    public static void printArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.println(i + ": " + array[i]);
        }
    }

    public static int[] removeElement(int[] array, int index)
    {
        if (index < 0 || index >= array.length)
        {
            System.out.println("Неверный индекс.");
            return array;
        }

        int[] newArray = new int[array.length - 1];

        for (int i = 0; i < index; i++)
        {
            newArray[i] = array[i];
        }

        for (int i = index; i < array.length - 1; i++)
        {
            newArray[i] = array[i + 1];
        }

        return newArray;
    }
}
