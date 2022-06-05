package pl.major.filip.organize_it.controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pl.major.filip.organize_it.model.topic.Note;

public class NoteListController {
    private final List<Note> notes = new ArrayList<>();

    public void addNote(Note note){
        notes.add(note);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateNote(int id, Note newNote){
        Optional<Note> noteOpt = notes.stream()
                .filter(n -> n.getId() == id)
                .findFirst();
        if (noteOpt.isPresent()) {
            Note oldNote = noteOpt.get();
            oldNote.setTopic(newNote.getTopic());
            oldNote.setDescription(newNote.getDescription());
            oldNote.setTitle(newNote.getTitle());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void deleteNote(int id){
        Optional<Note> noteOpt = notes.stream()
                .filter(n -> n.getId() == id)
                .findFirst();
        noteOpt.ifPresent(notes::remove);
    }

    public List<Note> getNotes(){
        return notes;
    }
}
