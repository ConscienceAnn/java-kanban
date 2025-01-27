package main.model;

import main.status.StatusTask;
import main.status.TaskType;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds = new ArrayList<>();


    public Epic(String name, String description, StatusTask status, ArrayList<Integer> subtaskIds) {
        super(name, description, status);
        this.subtaskIds = subtaskIds;
        this.taskType = TaskType.EPIC;
    }

    public Epic(String name, String description, int id, StatusTask status, ArrayList<Integer> subtaskIds) {
        super(name, description, id, status);
        this.subtaskIds = subtaskIds;
        this.taskType = TaskType.EPIC;
    }

    public Epic(String name, String description, StatusTask status) {
        super(name, description, status);
        this.taskType = TaskType.EPIC;
    }

    public Epic(String name, String description) {
        super(name, description);
        this.taskType = TaskType.EPIC;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EPIC;
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(ArrayList<Integer> subtaskIds) {
        this.subtaskIds = subtaskIds;
    }

    @Override
    public String toString() {
        return "Task.Epic{" +
                "id=" + getId() +
                ", Task.TaskType = '" + TaskType.EPIC +
                "name='" + getName() +
                ", description='" + getDescription() +
                ", statusTask='" + getStatus() +
                ", subtaskIDs=" + subtaskIds +
                '}';
    }

    public void addSubtaskIds(int idSubtask) {
        subtaskIds.add(idSubtask);
    }
}
