
package main.manager;

import main.model.Epic;
import main.status.StatusTask;
import main.model.Subtask;
import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTaskManager implements TaskManager {

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    int generateId = 1;
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public int createTask(Task task) {
        task.setId(generateId++);
        task.setStatus(StatusTask.NEW);
        tasks.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int createEpic(Epic epic){
        epic.setId(generateId++);
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    @Override
    public int createSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return subtask.getId();
        }
        subtask.setId(generateId++);
        subtasks.put(subtask.getId(), subtask);
        epic.getSubtaskIds().add(subtask.getId());
        updateEpicStatus(epic.getId());
        return subtask.getId();
    }

    @Override
    public void deleteTaskOnId(int id){
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }


    @Override
    public void deleteSubtaskOnId(int id) {
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            int subtask = subtasks.get(id).getEpicId();
            int idEpic = epics.get(subtask).getId();
            ArrayList<Integer> listIds = epics.get(idEpic).getSubtaskIds();
            listIds.remove(subtask);
            updateEpicStatus(epics.get(subtask).getId());
        }
    }


    @Override
    public void deleteEpicOnId(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.remove(id);
            for (int subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
        }
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubtasks(){
        subtasks.clear();
        for (Epic epic : epics.values()) {
            ArrayList<Integer> listIds = epic.getSubtaskIds();
            listIds.clear();
            int epicId = epic.getId();
            updateEpicStatus(epicId);
        }
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Task getTaskOnId(int id) {
        if (tasks.containsKey(id)) {
            historyManager.add(tasks.get(id));
            return tasks.get(id);
        } return null;
    }

    @Override
    public Epic getEpicOnId(int id) {
        if (epics.containsKey(id)) {
            historyManager.add(epics.get(id));
            return epics.get(id);
        } return null;
    }

    @Override
    public Subtask getSubtaskOnId(int id) {
        if (subtasks.containsKey(id)) {
            historyManager.add(subtasks.get(id));
            return subtasks.get(id);
        } return null;
    }

    @Override
    public ArrayList<Task> getAllTask() {
        return new ArrayList<>(this.tasks.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtask() {
        return new ArrayList<>(this.subtasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpic() {
        return new ArrayList<>(this.epics.values());
    }

    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())){
            subtasks.put(subtask.getId(), subtask);
            int epicId = subtask.getEpicId();
            updateEpicStatus(epicId);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())){
            tasks.put(epic.getId(), epic);
        }
    }

    @Override
    public ArrayList<Subtask> getSubtasksOnEpic(int id) {
        ArrayList<Subtask> listSubtasks = new ArrayList<>();
        for (int i : epics.get(id).getSubtaskIds()) {
            listSubtasks.add(subtasks.get(i));
        }
        return listSubtasks;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    private void updateEpicStatus(int epicId) {
        int countNEW = 0;
        int countDONE = 0;
       int countSubtasks = epics.get(epicId).getSubtaskIds().size();

        for (int subtaskID : epics.get(epicId).getSubtaskIds()) {
            if (subtasks.get(subtaskID).getStatus() == StatusTask.NEW) {
                countNEW++;
            } else if (subtasks.get(subtaskID).getStatus() == StatusTask.DONE) {
                countDONE++;
            }
        }
        if ((countNEW == countSubtasks) || (countSubtasks == 0)) {
            Epic epic = epics.get(epicId);
            epic.setStatus(StatusTask.NEW);
            epics.put(epicId, epic);
        } else if (countDONE == countSubtasks) {
            Epic epic = epics.get(epicId);
            epic.setStatus(StatusTask.DONE);
            epics.put(epicId, epic);
        } else {
            Epic epic = epics.get(epicId);
            epic.setStatus(StatusTask.IN_PROGRESS);
            epics.put(epicId, epic);
        }
    }

    public static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTask()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpic()) {
            System.out.println(epic);

            for (Task task : manager.getSubtasksOnEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtask()) {
            System.out.println(subtask);
        }

    }

}
