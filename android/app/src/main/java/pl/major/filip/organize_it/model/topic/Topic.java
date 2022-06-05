package pl.major.filip.organize_it.model.topic;

import java.util.List;

public class Topic {
    private String name;
    private String subject;
    private List<Note> notes;

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public Topic(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}
