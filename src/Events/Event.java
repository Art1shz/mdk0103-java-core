package Events;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String title;
    private String text;
    private LocalDateTime date;

    public Event(int id, String title, String text, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public Event(String title, String text, LocalDateTime date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
} 