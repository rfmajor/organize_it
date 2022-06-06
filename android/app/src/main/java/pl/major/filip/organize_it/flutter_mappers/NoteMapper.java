package pl.major.filip.organize_it.flutter_mappers;

import java.util.Map;

import pl.major.filip.organize_it.model.topic.Note;

public class NoteMapper {
    public Note mapToNote(Map<String, String> map) {
        String title = map.get("title");
        String description = map.get("description");

        return new Note(title, description);
    }
}
