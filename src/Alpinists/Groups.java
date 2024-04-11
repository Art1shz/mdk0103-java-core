package Alpinists;

public class Groups {
    private boolean isOpen;
    private static Alpinists[] group = Alpinists.alpinistsArray;
    private final String mountainName;

    public Groups(boolean isOpen, Alpinists[] group, String mountainName) {
        this.isOpen = isOpen;
        this.group = group;
        this.mountainName = mountainName;
    }

    public boolean getIsOpen() {
        if (isOpen)
            return true;
        else
            return false;
    }

    public String getMountainName() {
        return mountainName;
    }

    public boolean setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
        return isOpen;
    }

    static Groups group1 = new Groups(true, new Alpinists[15], "Эверест");
    static Groups group2 = new Groups(true, new Alpinists[15], "Монблан");
    static Groups group3 = new Groups(true, new Alpinists[15], "Килиманджаро");

    public void addToGroup1(Alpinists alpinist) {
        if (isOpen) {
            for (int i = 0; i < 3; i++) {
                if (group[i] == null) {
                    group[i] = alpinist;
                    System.out.println("Альпинист " + alpinist.getName() + " добавлен в группу");
                    return;
                }
            }
            System.out.println("Группа заполнена.");
        } else {
            System.out.println("Набор в группу закрыт");
        }
    }

    public void addToGroup2(Alpinists alpinist) {
        if (isOpen) {
            for (int i = 3; i < 8; i++) {
                if (group[i] == null) {
                    group[i] = alpinist;
                    System.out.println("Альпинист " + alpinist.getName() + " добавлен в группу");
                    return;
                }
            }
            System.out.println("Группа заполнена.");
        } else {
            System.out.println("Набор в группу закрыт");
        }
    }





    public void addToGroup3(Alpinists alpinist) {
        if (isOpen) {
            for (int i = 8; i < 15; i++) {
                if (group[i] == null) {
                    group[i] = alpinist;
                    System.out.println("Альпинист " + alpinist.getName() + " добавлен в группу");
                    return;
                }
            }
            System.out.println("Группа заполнена.");
        } else {
            System.out.println("Набор в группу закрыт");
        }
    }



    public static void getGroup1()
    {
        for (int i = 0; i < 3; i++)
        {
            if (group[i] != null)
            {
                System.out.println("Имя: " + group[i].getName() + " Возраст: " + group[i].getAge() + " Адрес: " + group[i].getAddress());
            }
            else
            {
                break;
            }
        }
    }

    public static void getGroup2()
    {
        for (int i = 3; i < 8; i++)
        {
            if (group[i] != null)
            {
                System.out.println("Имя: " + group[i].getName() + " Возраст: " + group[i].getAge() + " Адрес: " + group[i].getAddress());
            }
            else
            {
                break;
            }
        }
    }

    public static void getGroup3()
    {
        for (int i = 8; i < group.length; i++)
        {
            if (group[i] != null)
            {
                System.out.println("Имя: " + group[i].getName() + " Возраст: " + group[i].getAge() + " Адрес: " + group[i].getAddress());
            }
            else
            {
                break;
            }
        }
    }
}