package pl.major.filip.organize_it.flutter_mappers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.major.filip.organize_it.model.task.SimpleTask;
import pl.major.filip.organize_it.model.task.TaskStatus;
import pl.major.filip.organize_it.model.topic.Note;
import pl.major.filip.organize_it.model.topic.Topic;

public class SimpleTaskMapper implements TaskMapper<SimpleTask> {
    @Override
    public Map<String, String> taskToMap(SimpleTask task) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "simple");

        map.put("title", task.getTitle());

        Topic topic = task.getTopic();
        map.put("topicName", topic.getName());
        map.put("topicSubject", topic.getSubject());

        map.put("date", task.getDate().toString());

        map.put("status", String.valueOf(task.getStatus().ordinal()));

        List<Note> notes = topic.getNotes();
        for (Note note : notes) {
            String key = "note" + note.getId();
            //note to self: make it impossible to type ';' into the title
            String content = "title:" + note.getTitle() + ";description:" + note.getDescription();
            map.put(key, content);
        }
        return map;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public SimpleTask mapToTask(Map<String, String> map) {
        String title = map.get("title");
        String topicName = map.get("topicName");
        String topicSubject = map.get("topicSubject");
        LocalDate date = LocalDate.parse((String) map.get("date"));
        TaskStatus status = TaskStatus.values()[Integer.parseInt(map.get("status"))];

        Topic topic = new Topic(topicName, topicSubject);

        List<Note> notes = new ArrayList<>();
        map.forEach((name, noteContent) -> {
            if (name.contains("note") ) {
                int id = Integer.parseInt(name.substring(4));
                String[] split = noteContent.split(";");
                String noteTitle = split[0].substring(6);
                String noteDescription = split[1].substring(12);

                Note note = new Note(id, noteTitle, noteDescription);
                note.setTopic(topic);

                notes.add(note);
            }
        });
        topic.setNotes(notes);

        return SimpleTask.builder()
                .setTitle(title)
                .setTopic(topic)
                .setDate(date)
                .setStatus(status)
                .build();
    }
}
