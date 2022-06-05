package pl.major.filip.organize_it.model.topic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Objects;

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

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(name, topic.name) && Objects.equals(subject, topic.subject)
                && Objects.equals(notes, topic.notes);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, subject, notes);
    }
}
