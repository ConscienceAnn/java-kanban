package main.manager;

import main.model.Epic;
import main.model.Subtask;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    int createTask(Task task);

    int createEpic(Epic epic);

    int createSubtask(Subtask subtask);

    void deleteTaskOnId(int id);

    void deleteSubtaskOnId(int id);

    void deleteEpicOnId(int id);

    void deleteAllTasks();

    void deleteAllSubtasks();

    void deleteAllEpics();

    Task getTaskOnId(int id);

    Epic getEpicOnId(int id);

    Subtask getSubtaskOnId(int id);

    ArrayList<Task> getAllTask();

    ArrayList<Subtask> getAllSubtask();

    ArrayList<Epic> getAllEpic();

    void updateTask(Task task);

    void updateSubtask(Subtask subtask);

    void updateEpic(Epic epic);

    ArrayList<Subtask> getSubtasksOnEpic(int id);

    List<Task> getHistory();
}
