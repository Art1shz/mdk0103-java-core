package MessageCollection;

public class Message {
    public enum Priority {
        низкий, средний, высокий, срочный
    }

    private final int code;
    private final String text;
    private final Priority priority;

    public Message(int code, String text, Priority priority) {
        this.code = code;
        this.text = text;
        this.priority = priority;
    }

    public int getCode() {
        return code;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString()
    {
        return "Сообщение{" +
                "код=" + code +
                ", текст='" + text + '\'' +
                ", приоритет=" + priority +
                '}';
    }

    public boolean isEqual(Message other) {
        return this.code == other.code &&
                this.text.equals(other.text) &&
                this.priority == other.priority;
    }
}
