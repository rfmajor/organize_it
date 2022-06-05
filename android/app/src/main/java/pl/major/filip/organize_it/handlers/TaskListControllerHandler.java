package pl.major.filip.organize_it.handlers;

import androidx.annotation.NonNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import pl.major.filip.organize_it.controllers.ReoccurringTaskListController;

public class TaskListControllerHandler implements MethodChannel.MethodCallHandler {

    private ReoccurringTaskListController controller;

    public TaskListControllerHandler(ReoccurringTaskListController controller) {
        this.controller = controller;
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        if (call.method.equals("saveState")) {
            saveState();
        } else {
            result.notImplemented();
        }
    }

    public void saveState() {
        try (FileOutputStream fos = new FileOutputStream("yourfile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(controller);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
