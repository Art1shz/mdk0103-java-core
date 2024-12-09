package MessageCollection;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageGenerator {
    private static final Random RANDOM = new Random();
    private static final String[] TEXTS = {"Сообщение 1", "Сообщение 2", "Сообщение 3", "Сообщение 4", "Сообщение 5"};

    public static List<Message> generateMessages(int count) {
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int code = RANDOM.nextInt(50);
            String text = TEXTS[RANDOM.nextInt(TEXTS.length)];
            Message.Priority priority = Message.Priority.values()[RANDOM.nextInt(Message.Priority.values().length)];
            messages.add(new Message(code, text, priority));
        }
        return messages;
    }
}
