package pl.major.filip.organize_it.flutter_serialization;

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
    public Map<String, Object> taskToMap(SimpleTask task) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "simple");

        map.put("title", task.getTitle());

        Topic topic = task.getTopic();
        map.put("topicName", topic.getName());
        map.put("topicSubject", topic.getSubject());

        map.put("date", task.getDate().toString());

        map.put("status", task.getStatus().ordinal());

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
    public SimpleTask mapToTask(Map<String, Object> map) {
        String title = (String) map.get("title");
        String topicName = (String) map.get("topicName");
        String topicSubject = (String) map.get("topicSubject");
        LocalDate date = LocalDate.parse((String) map.get("date"));
        TaskStatus status = TaskStatus.values()[(int) map.get("status")];

        List<Note> notes = new ArrayList<>();
        map.forEach((name, val) -> {
            if (name.contains("note") ) {
                int id = Integer.parseInt(name.substring(4));
                String noteContent = (String) val;
                String[] split = noteContent.split(";");
                String noteTitle = split[0].substring(6);
                String noteDescription = split[1].substring(12);

                notes.add(new Note(id, noteTitle, noteDescription));
            }
        });

        Topic topic = new Topic(topicName, topicSubject);

        return SimpleTask.builder()
                .setTitle(title)
                .setTopic(topic)
                .setDate(date)
                .setStatus(status)
                .build();
    }
}
