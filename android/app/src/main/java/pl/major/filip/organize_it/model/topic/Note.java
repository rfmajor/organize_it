package pl.major.filip.organize_it.model.topic;

public class Note extends Attachment {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note(String title) {
        super(title);
    }
}
