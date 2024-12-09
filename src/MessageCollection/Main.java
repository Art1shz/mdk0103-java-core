package MessageCollection;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Message> generatedMessages = MessageGenerator.generateMessages(20);
        MessageCollection collection = new MessageCollection(generatedMessages);

        System.out.println("Сгенерированные сообщения:");
        collection.printMessages();

        System.out.println("\nУникальные сообщения:");
        List<Message> uniqueMessages = collection.getUniqueMessages();
        uniqueMessages.forEach(System.out::println);

        for (Message.Priority priority : Message.Priority.values()) {
            int count = collection.countMessagesByPriority(priority);
            System.out.printf("Количество сообщений с приоритетом %s: %d%n", priority, count);
        }

        int testCode = 42;
        int codeCount = collection.countMessagesByCode(testCode);
        System.out.printf("\nКоличество сообщений с кодом %d: %d%n", testCode, codeCount);

        int uniqueCount = collection.countUniqueMessages();
        System.out.printf("\nКоличество уникальных сообщений: %d%n", uniqueCount);

        System.out.println("\nУдаление сообщений с приоритетом высокий:");
        collection.removeMessagesByPriority(Message.Priority.высокий);

        System.out.println("\nОставить только сообщения с приоритетом средний");
        collection.retainMessagesByPriority(Message.Priority.средний);
    }
}
