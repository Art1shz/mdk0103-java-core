package MessageCollection;


import java.util.ArrayList;
import java.util.List;

public class MessageCollection {
    private final List<Message> messages;

    public MessageCollection(List<Message> messages) {
        this.messages = new ArrayList<>(messages);
    }

    public List<Message> getUniqueMessages() {
        List<Message> uniqueMessages = new ArrayList<>();
        for (Message message : messages) {
            boolean isDuplicate = false;
            for (Message unique : uniqueMessages) {
                if (message.isEqual(unique)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueMessages.add(message);
            }
        }
        return uniqueMessages;
    }

    public void removeMessagesByPriority(Message.Priority priority) {
        System.out.println("До удаления: " + messages);
        messages.removeIf(message -> message.getPriority() == priority);
        System.out.println("После удаления: " + messages);
    }

    public void retainMessagesByPriority(Message.Priority priority) {
        System.out.println("До удаления: " + messages);
        messages.removeIf(message -> message.getPriority() != priority);
        System.out.println("После удаления: " + messages);
    }

    public int countMessagesByPriority(Message.Priority priority) {
        int count = 0;
        for (Message message : messages) {
            if (message.getPriority() == priority) {
                count++;
            }
        }
        return count;
    }

    public int countMessagesByCode(int code) {
        int count = 0;
        for (Message message : messages) {
            if (message.getCode() == code) {
                count++;
            }
        }
        return count;
    }

    public int countUniqueMessages() {
        int uniqueCount = 0;
        for (int i = 0; i < messages.size(); i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < i; j++) {
                if (messages.get(i).isEqual(messages.get(j))) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniqueCount++;
            }
        }
        return uniqueCount;
    }

    public void printMessages() {
        for (Message message : messages) {
            System.out.println(message);
        }
    }
}
