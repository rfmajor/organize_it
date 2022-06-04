package pl.major.filip.organize_it.model.topic;

public abstract class Attachment {
    private String title;
    private Topic topic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Attachment(String title) {
        this.title = title;
    }

    public Attachment(String title, Topic topic) {
        this.title = title;
        this.topic = topic;
    }
}
