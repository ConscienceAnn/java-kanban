package main.model;

import main.status.StatusTask;
import main.status.TaskType;

public class Subtask extends Task {
    private int epicId;


    public Subtask(String name, String description, StatusTask status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
        this.taskType = TaskType.SUBTASK;
    }

    public Subtask(String name, String description, Integer epicId) {
        super(name, description);
        this.epicId = epicId;
        this.taskType = TaskType.SUBTASK;
    }


    @Override
    public TaskType getTaskType() {
        return TaskType.SUBTASK;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Task.Subtask{" +
                "id=" + getId() +
                ", Task.TaskType = " + TaskType.SUBTASK +
                "name='" + getName() +
                ", description='" + getDescription() +
                ", statusTask=" + getStatus() +
                ", epicId=" + epicId +
                '}';

    }
}
