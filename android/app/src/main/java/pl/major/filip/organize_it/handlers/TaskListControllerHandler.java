package pl.major.filip.organize_it.handlers;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import pl.major.filip.organize_it.controllers.ReoccurringTaskListController;
import pl.major.filip.organize_it.controllers.SimpleTaskListController;
import pl.major.filip.organize_it.flutter_mappers.ReoccurringTaskMapper;
import pl.major.filip.organize_it.flutter_mappers.SimpleTaskMapper;
import pl.major.filip.organize_it.model.task.ReoccurringTask;
import pl.major.filip.organize_it.model.task.SimpleTask;

public class TaskListControllerHandler implements MethodChannel.MethodCallHandler {

    private final ReoccurringTaskListController reoccurringTaskController = new ReoccurringTaskListController();
    private final SimpleTaskListController simpleTaskController = new SimpleTaskListController();

    private final ReoccurringTaskMapper reoccurringMapper = new ReoccurringTaskMapper();
    private final SimpleTaskMapper simpleMapper = new SimpleTaskMapper();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if (call.method.equals("refresh")) {
            simpleTaskController.refresh();
            reoccurringTaskController.refresh();
            return;
        }
        else if (call.method.equals("updateTask")) {
            Map<String, String> oldTask = (Map<String, String>) call.argument("oldTask");
            Map<String, String> newTask = (Map<String, String>) call.argument("newTask");

            if (isSimpleTask(oldTask)) {
                SimpleTask newSimpleTask = simpleMapper.mapToTask(newTask);
                SimpleTask oldSimpleTask = simpleMapper.mapToTask(oldTask);
                simpleTaskController.updateTask(oldSimpleTask, newSimpleTask);
            }
            else {
                ReoccurringTask newReoccurringTask = reoccurringMapper.mapToTask(newTask);
                ReoccurringTask oldReoccurringTask = reoccurringMapper.mapToTask(oldTask);
                reoccurringTaskController.updateTask(oldReoccurringTask, newReoccurringTask);
            }
            return;
        }

        Map<String, String> task = (Map<String, String>) call.argument("task");

        switch (call.method) {
            case "saveState":
                saveState();
                break;
            case "addTask":
                if (isSimpleTask(task)) {
                    SimpleTask simpleTask = simpleMapper.mapToTask(task);
                    simpleTaskController.addTask(simpleTask);
                } else {
                    ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(task);
                    reoccurringTaskController.addTask(reoccurringTask);
                }
                break;
            case "deleteTask":
                if (isSimpleTask(task)) {
                    SimpleTask simpleTask = simpleMapper.mapToTask(task);
                    simpleTaskController.deleteTask(simpleTask);
                } else {
                    ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(task);
                    reoccurringTaskController.deleteTask(reoccurringTask);
                }
                break;
            case "cancel":
                if (isSimpleTask(task)) {
                    SimpleTask simpleTask = simpleMapper.mapToTask(task);
                    simpleTaskController.cancelTask(simpleTask);
                } else {
                    ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(task);
                    reoccurringTaskController.cancelTask(reoccurringTask);
                }
                break;
            case "getTasks":
                List<Map<String, String>> tasks = new ArrayList<>();
                tasks.addAll(
                        simpleTaskController
                                .getTasks()
                                .stream()
                                .map(simpleMapper::taskToMap)
                                .collect(Collectors.toList()));
                tasks.addAll(
                        reoccurringTaskController
                                .getTasks()
                                .stream()
                                .map(reoccurringMapper::taskToMap)
                                .collect(Collectors.toList()));

                result.success(tasks);
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    public void saveState() {
        try (FileOutputStream fos = new FileOutputStream("yourfile.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(reoccurringTaskController);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean isSimpleTask(Map<String, String> args) {
        return args.getOrDefault("type", "").equals("simple");
    }
}
