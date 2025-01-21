package main.manager;

import main.exception.ManagerSaveException;
import main.model.Epic;
import main.model.Subtask;
import main.model.Task;
import main.status.StatusTask;
import main.status.TaskType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }


    @Override
    public int createTask(Task task) {
    int id = super.createTask(task);
    save();
    return id;
    }

    @Override
    public int createEpic(Epic epic) {
        int id = super.createEpic(epic);
        save();
        return id;
    }

    @Override
    public int createSubtask(Subtask subtask) {
        int id = super.createSubtask(subtask);
        save();
        return id;
    }

    @Override
    public  void deleteTaskOnId(int id) {
        super.deleteTaskOnId(id);
        save();
    }

    @Override
    public void deleteSubtaskOnId(int id) {
        super.deleteSubtaskOnId(id);
        save();
    }

    @Override
    public void deleteEpicOnId(int id) {
        super.deleteEpicOnId(id);
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public Task getTaskOnId(int id) {
        Task task = super.getTaskOnId(id);
        save();
        return task;
    }

    @Override
    public Epic getEpicOnId(int id) {
        Epic epic = super.getEpicOnId(id);
        save();
        return epic;
    }

    @Override
    public Subtask getSubtaskOnId(int id) {
        Subtask subtask = super.getSubtaskOnId(id);
        save();
        return subtask;
    }

    @Override
    public ArrayList<Task> getAllTask() {
        ArrayList<Task> task = super.getAllTask();
        save();
        return task;
    }

    @Override
    public ArrayList<Subtask> getAllSubtask() {
        ArrayList<Subtask> subtask = super.getAllSubtask();
        save();
        return subtask;
    }

    @Override
    public ArrayList<Epic> getAllEpic() {
        ArrayList<Epic> epic = super.getAllEpic();
        save();
        return epic;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public ArrayList<Subtask> getSubtasksOnEpic(int id) {
        ArrayList<Subtask> subtasks = super.getSubtasksOnEpic(id);
        save();
        return subtasks;
    }

    @Override
    public List<Task> getHistory() {
        List<Task> taskHistory = super.getHistory();
        save();
        return taskHistory;
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write("id,type,name,description,status,epicid\n");
            for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
                writer.write(getTaskOnId(entry.getKey()).toString() + "\n");
            }

            for (Map.Entry<Integer, Epic> entry : epics.entrySet()) {
                writer.write(getEpicOnId(entry.getKey()).toString() + "\n");
            }

            for (Map.Entry<Integer, Subtask> entry : subtasks.entrySet()) {
                writer.write(getSubtaskOnId(entry.getKey()).toString() + "\n");
            }

        } catch (IOException exception) {
            throw new ManagerSaveException("Не удалось сохранить");
        }
    }

    private static Task fromString(String value) {

        String[] values = value.split(",");
        String id = values[0];
        String type = values[1];
        String name = values[2];
        String taskStatus = values[3];
        String description = values[4];
        Integer epicId = type.equals(TaskType.SUBTASK.toString()) ? Integer.valueOf(values[7]) : null;

        switch (type) {

            case "EPIC":
                Epic epic = new Epic(name, description);
                epic.setId(Integer.parseInt(id));
                epic.setStatus(StatusTask.valueOf(taskStatus.toUpperCase()));
                return epic;

            case "SUBTASK":
                Subtask subtask = new Subtask(name, description, epicId);
                subtask.setId(Integer.parseInt(id));
                subtask.setStatus(StatusTask.valueOf(taskStatus.toUpperCase()));
                return subtask;

            case "TASK":
                Task task = new Task(name, description);
                task.setId(Integer.parseInt(id));
                task.setStatus(StatusTask.valueOf(taskStatus.toUpperCase()));
                return task;

            default:
                return null;

        }

    }

    public void loadFromFile(File file) {

        FileBackedTaskManager backedManager;
        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            backedManager = new FileBackedTaskManager(file);

            while (reader.ready()) {
                line = reader.readLine();
                if (line.isEmpty()) {
                    continue;
                }

                String[] lines = line.split(",");

                if (lines[1].equalsIgnoreCase("TASK")) {
                    Task task = fromString(line);
                    tasks.put(Objects.requireNonNull(task).getId(), task);
                } else if (lines[1].equalsIgnoreCase("EPIC")) {
                    Task task = fromString(line);
                    epics.put(Objects.requireNonNull(task).getId(), (Epic) task);
                } else if (lines[1].equalsIgnoreCase("SUBTASK")) {
                    Task task = fromString(line);
                    subtasks.put(Objects.requireNonNull(task).getId(), (Subtask) task);
                }
            }

        } catch (IOException exception) {
            throw new ManagerSaveException("Ошибка при получении данных их файла");
        }

    }

}
