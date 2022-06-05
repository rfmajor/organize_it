package pl.major.filip.organize_it.model.topic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && Objects.equals(description, note.description)
                && Objects.equals(getTitle(), note.getTitle());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, description, getTitle());
    }
}
