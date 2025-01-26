package main.model;

import main.status.StatusTask;
import main.status.TaskType;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private int id;
    private StatusTask status;
    protected TaskType taskType;

    public Task(String name, String description, StatusTask status) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.taskType = TaskType.TASK;
    }

    public Task(String name, String description, int id, StatusTask status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
        this.taskType = TaskType.TASK;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.taskType = TaskType.TASK;
    }

    public TaskType getTaskType() {
        return TaskType.TASK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }

    @Override
    public String toString() {
        return "Task.Task{" +
                "id=" + getId() +
                ", Task.TaskType = '" + TaskType.TASK +
                ", nameTask='" + getName() +
                ", descriptionTask='" + getDescription() +
                ", statusTask='" + getStatus() +
                '}';
    }

}
