package pl.major.filip.organize_it.flutter_mappers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.major.filip.organize_it.model.task.ReoccurringTask;
import pl.major.filip.organize_it.model.task.TaskStatus;
import pl.major.filip.organize_it.model.topic.Note;
import pl.major.filip.organize_it.model.topic.Topic;

public class ReoccurringTaskMapper implements TaskMapper<ReoccurringTask> {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Map<String, String> taskToMap(ReoccurringTask task) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "reoccurring");

        map.put("title", task.getTitle());

        Topic topic = task.getTopic();
        map.put("topicName", topic.getName());
        map.put("topicSubject", topic.getSubject());

        map.put("startDate", task.getStartDate().toString());
        map.put("endDate", task.getEndDate().toString());

        map.put("daysOfWeek", task.getDaysOfWeekString());

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
    public ReoccurringTask mapToTask(Map<String, String> map) {
        String title = map.get("title");
        String topicName = map.get("topicName");
        String topicSubject = map.get("topicSubject");

        LocalDate startDate = LocalDate.parse(map.get("startDate"));
        LocalDate endDate = LocalDate.parse(map.get("endDate"));

        TaskStatus status = TaskStatus.values()[Integer.parseInt(map.get("status"))];

        Set<DayOfWeek> daysOfWeek = ReoccurringTask.getDaysOfWeekFromString(map.get("daysOfWeek"));

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

        return ReoccurringTask.builder()
                .setTitle(title)
                .setTopic(topic)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setDaysOfWeek(daysOfWeek)
                .setStatus(status)
                .build();
    }
}
