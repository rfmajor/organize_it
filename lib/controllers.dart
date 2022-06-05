import 'package:flutter/services.dart';
import 'package:collection/collection.dart';
import 'dart:async';

class TaskListController {
  List<Map<String, String>> tasks = [];
  static const methodChannel = MethodChannel('javaMethodChannel');
  static const equality = MapEquality();

  Future<Null> addTask(Map<String, String> task) async {
    try {
      await methodChannel.invokeMethod('addTask', task);
    } on Exception catch (e) {

    }
    tasks.add(task);
  }

  Future<Null> updateTask(Map<String, String> oldTask, Map<String, String> newTask) async {
    try {
      await methodChannel.invokeMethod('updateTask', [oldTask, newTask]);
    } on Exception catch (e) {

    }
    for (var i = 0; i < tasks.length; i++) {
      if (equality.equals(tasks[i], oldTask)) {
        tasks[i] = newTask;
      }
    }
  }

  Future<Null> deleteTask(Map<String, String> task) async {
    try {
      await methodChannel.invokeMethod('deleteTask', task);
    } on Exception catch (e) {

    }
    for (var i = 0; i < tasks.length; i++) {
      if (equality.equals(tasks[i], task)) {
        tasks.removeAt(i);
      }
    }
  }

  Future<Null> cancelTask(Map<String, String> task) async {
    try {
      await methodChannel.invokeMethod('cancelTask', task);
    } on Exception catch (e) {

    }
    for (var i = 0; i < tasks.length; i++) {
      if (equality.equals(tasks[i], task)) {
        tasks[i]["status"] = "3";
      }
    }
  }

  Future<Null> getTasks() async {
    try {
      List<Map<String, String>> newTasks = await methodChannel.invokeMethod('getTasks');
      tasks = newTasks;
    } on Exception catch (e) {

    }
  }
}