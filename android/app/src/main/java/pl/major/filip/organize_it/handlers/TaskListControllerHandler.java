package pl.major.filip.organize_it.handlers;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

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
        if (call.method.equals("saveState")) {
            saveState();
        }
        else if (call.method.equals("addTask")) {
            Map<String, Object> args = (Map<String, Object>) call.arguments;
            if (args.get("type").equals("simple")) {
                SimpleTask simpleTask = simpleMapper.mapToTask(args);
                simpleTaskController.addTask(simpleTask);
            }
            else {
                ReoccurringTask reoccurringTask = reoccurringMapper.mapToTask(args);
                reoccurringTaskController.addTask(reoccurringTask);
            }
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
}
