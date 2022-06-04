package pl.major.filip.organize_it;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import pl.major.filip.organize_it.controllers.TaskListController;
import pl.major.filip.organize_it.handlers.TaskListControllerHandler;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "javaChannel";

    private TaskListController taskListController;

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        MethodChannel methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);

        TaskListControllerHandler handler = new TaskListControllerHandler(taskListController);

        methodChannel.setMethodCallHandler(handler);
    }
}
