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
import pl.major.filip.organize_it.flutter_serialization.ReoccurringTaskMapper;
import pl.major.filip.organize_it.flutter_serialization.SimpleTaskMapper;
import pl.major.filip.organize_it.model.task.ReoccurringTask;
import pl.major.filip.organize_it.model.task.SimpleTask;

public class TaskListControllerHandler implements MethodChannel.MethodCallHandler {

    private ReoccurringTaskListController reoccurringTaskController;
    private SimpleTaskListController simpleTaskController;

    private final ReoccurringTaskMapper reoccurringMapper = new ReoccurringTaskMapper();
    private final SimpleTaskMapper simpleMapper = new SimpleTaskMapper();

    public TaskListControllerHandler(ReoccurringTaskListController reoccurringTaskController,
                                     SimpleTaskListController simpleTaskController) {
        this.reoccurringTaskController = reoccurringTaskController;
        this.simpleTaskController = simpleTaskController;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if (call.method.equals("refresh")) {
            simpleTaskController.refresh();
            reoccurringTaskController.refresh();
            return;
        }

        Map<String, String> args = (Map<String, String>) call.arguments;

        if (call.method.equals("saveState")) {
            saveState();
        }
        else if (call.method.equals("addTask")) {
            if (isSimpleTask(args)) {
                SimpleTask simpleTask = simpleMapper.mapToTask(args);
                simpleTaskController.addTask(simpleTask);
            }
            else {
                ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(args);
                reoccurringTaskController.addTask(reoccurringTask);
            }
        }
        else if (call.method.equals("updateTask")) {
            if (isSimpleTask(args)) {
                SimpleTask simpleTask = simpleMapper.mapToTask(args);
                // TODO: 05.06.2022 implement this properly
                simpleTaskController.updateTask(simpleTask, simpleTask);
            }
            else {
                ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(args);
                // TODO: 05.06.2022 this as well
                reoccurringTaskController.updateTask(reoccurringTask, reoccurringTask);
            }
        }
        else if (call.method.equals("deleteTask")) {
            if (isSimpleTask(args)) {
                SimpleTask simpleTask = simpleMapper.mapToTask(args);
                simpleTaskController.deleteTask(simpleTask);
            }
            else {
                ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(args);
                reoccurringTaskController.deleteTask(reoccurringTask);
            }
        }
        else if (call.method.equals("cancel")) {
            if (isSimpleTask(args)) {
                SimpleTask simpleTask = simpleMapper.mapToTask(args);
                simpleTaskController.cancelTask(simpleTask);
            }
            else {
                ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(args);
                reoccurringTaskController.cancelTask(reoccurringTask);
            }
        }
        else if (call.method.equals("getTasks")) {
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
        }
        else {
            result.notImplemented();
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
