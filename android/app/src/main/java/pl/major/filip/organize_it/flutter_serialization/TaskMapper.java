package pl.major.filip.organize_it.flutter_serialization;

import java.util.Map;

import pl.major.filip.organize_it.model.task.Task;

public interface TaskMapper<T extends Task> {
    Map<String, Object> taskToMap(T task);
    T mapToTask(Map<String, Object> map);
}
