package PersonSort;

class Array
{
    private Person[] array;
    private int size;
    private int count;

    public Array(int size)
    {
        this.size = size;
        this.array = new Person[size];
        this.count = 0;
    }

    public void insert(Person person)
    {
        if (count < size)
        {
            array[count] = person;
            count++;
        }
        else
        {
            System.out.println("Массив заполнен");
        }
    }

    public void display()
    {
        for (int i = 0; i < count; i++)
        {
            System.out.println(array[i]);
        }
    }

    public void bubbleSort()
    {
        for (int i = 0; i < count - 1; i++)
        {
            for (int j = 0; j < count - i - 1; j++)
            {
                if (array[j].getAge() > array[j + 1].getAge())
                {
                    Person temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public void selectionSort()
    {
        for (int i = 0; i < count - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < count; j++)
            {
                if (array[j].getAge() < array[minIndex].getAge())
                {
                    minIndex = j;
                }
            }
            Person temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public void insertionSort()
    {
        for (int i = 1; i < count; i++)
        {
            Person current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].getAge() > current.getAge())
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }
}
