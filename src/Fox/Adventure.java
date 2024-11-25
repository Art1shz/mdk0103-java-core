package Fox;

import java.util.Scanner;

public class Adventure {
    private boolean gameRunning = false;
    private String currentState = "start";
    private final Scanner scan = new Scanner(System.in);

    public void startGame(Menu menu) {
        gameRunning = true;
        currentState = "start";
        gameLoop(menu);
    }

    public void continueGame(String savedState, Menu menu) {
        gameRunning = true;
        currentState = savedState;
        System.out.println("Игра загружена!");
        gameLoop(menu);
    }

    private void gameLoop(Menu menu) {
        while (gameRunning) {
            switch (currentState) {
                case "start" -> startState(menu);
                case "returnHome" -> returnHomeState();
                case "searchFriend" -> searchFriendState(menu);
                case "askResidents" -> askResidentsState(menu);
                case "trustOwl" -> trustOwlState(menu);
                case "trustWolf" -> trustWolfState(menu);
                case "exploreAlone" -> gameOver("Лисёнок заблудился. Теперь его самого нужно искать. \nВЫ ПРОИГРАЛИ");
                case "bearEncounter" -> bearEncounterState(menu);
                case "collectHoney" -> collectHoneyState(menu);
                case "waitForBees" -> waitForBeesState(menu);
                case "bringTheHoney"-> bringTheHoneyState(menu);
                default -> System.out.println("Некорректное состояние игры.");
            }
        }
    }

    private void startState(Menu menu) {
        System.out.println("""
                Каждое утро Лисёнок просыпался, завтракал и шёл увидеться с Бельчонком.
                Это утро не было исключением. Лисёнок пришёл на их обычное место встречи, но Бельчонка там не было.
                Лисёнок ждал, ждал, но так и не смог дождаться своего друга
                Бельчонок не пропустил еще ни одной встречи, вдруг он попал в беду. - подумал Лисёнок.
                                
                Как поступить Лисёнку?
                1. Вернуться домой
                2. Отправиться на поиски
                3. Показать меню
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "returnHome";
            case 2 -> currentState = "searchFriend";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void returnHomeState() {
        System.out.println("""
                Вернувшись домой, Лисёнок нашёл там Бельчонка.
                Оказалось, что Бельчонок пришёл на место встречи раньше и увидел там рой злобных пчел.
                Он поспешил предупредить об этом Лисёнка, но они разминулись. Наконец-то друзья нашли друг друга!
                
                ВЫ ВЫИГРАЛИ
                """);
        gameRunning = false;
    }

    private void searchFriendState(Menu menu) {
        System.out.println("""
                Все лесные жители были заняты своими делами и не обращали внимания на Лисёнка и его проблему.
                Но вдруг кто нибудь видел Бельчонка... Лисёнок не знал, что ему делать. Помогите ему.
                
                1. Попытаться разузнать о Бельчонке у лесных жителей
                2. Искать Бельчонка в одиночку
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "askResidents";
            case 2 -> currentState = "exploreAlone";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void askResidentsState(Menu menu) {
        System.out.println("""
                Пока Лисёнок принимал решение, лесные жители разошлись кто куда. Остались только Сова и Волк.
                Но у Совы бывают проблемы с памятью, а Волк может сильно разозлиться из-за расспросов
                
                Кого выбрать?
                1. Расспросить Сову
                2. Расспросить Волка
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "trustOwl";
            case 2 -> currentState = "trustWolf";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void trustOwlState(Menu menu) {
        System.out.println("""
                Сова долго не хотела говорить, но в итоге сказала, что видела испуганного Бельчонка, бежавшего вглубь леса.
                Все лесные жители знают, что в глубине леса опасно, если Бельчонок там, ему срочно нужна помощь.
                
                1. Поверить Сове и отправиться вглубь леса
                2. Сове не стоит верить
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "bearEncounter";
            case 2 -> currentState = "exploreAlone";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void trustWolfState(Menu menu) {
        System.out.println("""
                Волк оказался вполне дружелюбным, но помочь не смог. Лишь сказал, что маленькому лисенку не стоит бродить по лесу одному.
                И как теперь поступить?
                
                1. Волк прав
                2. Волку не стоит верить
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "returnHome";
            case 2 -> currentState = "exploreAlone";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void bearEncounterState(Menu menu) {
        System.out.println("""
                В глубине леса Лисёнок встретил Медвежонка. Ленивый Медвежонок был готов рассказать все, что знает, если Лисёнок принесёт ему мёда.
                
                1. Нет, потрачено слишком много времени, нужно идти дальше
                2. Нужно воспользоваться шансом и раздобыть мёд
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "exploreAlone";
            case 2 -> currentState = "collectHoney";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void collectHoneyState(Menu menu) {
        System.out.println("""
                Лисёнок быстро нашёл мёд, но вокруг летал рой злобных пчел.
                Лисёнок всегда боялся пчёл, но и не найти друга он тоже боялся.
                
                1. Подождать, вдруг пчёлы улетят
                2. Нужно попытаться выкрасть мёд немедленно
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "waitForBees";
            case 2 -> gameOver("""
                Это была не лучшая идея. Пчелы покусали Лисенка и теперь ему самому нужна помощь. Игра закончилась
                ВЫ ПРОИГРАЛИ
                """);
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void waitForBeesState(Menu menu) {
        System.out.println("""
                Лисёнок подождал немного, и пчёлы разлетелись. Лисёнок без проблем набрал мёда. Вдруг он понял, что очень голоден. Что же делать?
                
                1. Поесть немного и передохнуть
                2. Скорее отнести мёд Медвежонку
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> gameOver("""
                    Пока Лисёнок ел, злобные пчёлы вернулись и покусали его. Лисёнку нужна помощь, он не сможет продолжить поиски.
                    ВЫ ПРОИГРАЛИ
                    """);
            case 2 -> currentState = "bringTheHoney";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор.");
        }
    }

    private void bringTheHoneyState(Menu menu) {
        System.out.println("""
                Довольный Медвежонок рассказал Лисёнку, что очень хорошо знает семью Белок и уверен, что Бельчонок никогда не пошёл бы в глубь леса.
                Он заверял Лисёнка, что Белки не попадают в неприятности, и что Совам нельзя верить, он также уговаривал Лисёнка вернуться домой.
                
                1. Медвежонок ничего не знает, нужно продолжить поиски
                2. Может быть он прав и Лисёнок просто паникует
                3. Сохранить игру
                """);
        int choice = getChoice();

        switch (choice) {
            case 1 -> currentState = "exploreAlone";
            case 2 -> currentState = "returnHome";
            case 3 -> menu.saveGame(currentState);
            default -> System.out.println("Некорректный выбор");
        }
    }

    private void gameOver(String message) {
        System.out.println(message);
        gameRunning = false;
    }

    private int getChoice() {
        System.out.print("Введите выбор: ");
        return scan.nextInt();
    }
}
