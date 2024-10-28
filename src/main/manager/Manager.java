
// "Комментариями к работе, можешь задавать свои вопросы и по ошибкам и если не понятны какие-нибудь моменты"
// Это очень ценно! Благодарю!

package main.manager;

import main.model.Epic;
import main.status.StatusTask;
import main.model.Subtask;
import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;


public class Manager {

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    int generateId = 1;

    public int createTask(Task task) {
        task.setId(generateId++);
        task.setStatus(StatusTask.NEW);
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public int createEpic(Epic epic){
        epic.setId(generateId++);
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

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

    public void deleteTaskOnId (int id){
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }


    public void deleteSubtaskOnId (int id) {
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            int subtask = subtasks.get(id).getEpicId();
            int idEpic = epics.get(subtask).getId();
            ArrayList<Integer> listIds = epics.get(idEpic).getSubtaskIds();
            listIds.remove(subtask);
            updateEpicStatus(epics.get(subtask).getId());
        }
    }


    public void deleteEpicOnId (int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.remove(id);
            for (int subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
        }
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtasks(){
        subtasks.clear();
        for (Epic epic : epics.values()) {
            ArrayList<Integer> listIds = epic.getSubtaskIds();
            listIds.clear();
            int epicId = epic.getId();
            updateEpicStatus(epicId);
        }
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public Task getTaskOnId(int id) {
       return tasks.get(id);
    }

    public Epic getEpicOnId(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskOnId(int id) {
        return subtasks.get(id);
    }

    public ArrayList<Task> getAllTask() {
        return new ArrayList<>(this.tasks.values());
    }

    public ArrayList<Subtask> getAllSubtask() {
        return new ArrayList<>(this.subtasks.values());
    }

    public ArrayList<Epic> getAllEpic() {
        return new ArrayList<>(this.epics.values());
    }

    public void updateTask (Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateSubtask (Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())){
            subtasks.put(subtask.getId(), subtask);
            int epicId = subtask.getEpicId();
            updateEpicStatus(epicId);
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())){
            tasks.put(epic.getId(), epic);
        }
    }

    public ArrayList<Subtask> getSubtasksOnEpic(int id) {
        ArrayList<Subtask> listSubtasks = new ArrayList<>();
        for (int i : epics.get(id).getSubtaskIds()) {
            listSubtasks.add(subtasks.get(i));
        }
        return listSubtasks;
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

}
