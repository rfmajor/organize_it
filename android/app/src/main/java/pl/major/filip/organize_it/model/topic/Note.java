package pl.major.filip.organize_it.model.topic;

public class Note extends Attachment {
    private int id;
    private String description;

    private static int nextId = 0;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note(String title, String description) {
        super(title);
        this.description = description;
        this.id = nextId++;
    }

    public Note(int id, String title, String description) {
        super(title);
        this.description = description;
        this.id = id;
    }
}
