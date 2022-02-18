package com.example.thexanhcovid19;

public class TaskModel {

    private String task_name;
    private String  task_added_time;


    public TaskModel(String taskName, String  taskAddedTime) {
        this.task_name = taskName;
        this.task_added_time = taskAddedTime;
    }

    public String getTaskName() {
        return task_name;
    }

    public String getTaskAddedTime() {
        return task_added_time;
    }
}
