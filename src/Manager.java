// Патимат, добрый день!
// Я только сейчас догадалась обратиться к тебе тут. Благодарю за твои ревью и комментарии!
// Касательно данной задачи: Main у меня не заработал, хотя уже даже подсказкой из Пачки воспользовалась.
// по Manager я уже 4ый раз переписываю, хожу кругами, уже начала подсматривать в чужие подсказки/обсуждения.
// Но решила уже отправить, чтобы сдвинуться хоть куда-то.
// Подсвети, пожалуйста, мои ошибки. И если где допустимо/возможно текстом указать логику.
// Благодарю!

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
        if (epics.get(subtask.getEpicId()) != null) {
            subtask.setId(generateId++);
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getId());
            if (epic == null) {
                return 0;
            }
            epic.getSubtaskIds().add(subtask.getId());
            updateEpicStatus(epics.get(subtask.getEpicId()).getId());
        }
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
            updateEpicStatus(epics.get(subtask).getId());
        }
    }

    public void deleteEpicOnId (int id) {
        if (epics.containsKey(id)) {
            epics.remove(id);
            for (int i : epics.get(id).getSubtaskIds()) {
                subtasks.remove(i);
            }
        }
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtask(){
        subtasks.clear();
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
        ArrayList<Task> listTasks = new ArrayList<>();
        for (int i : tasks.keySet()) {
            listTasks.add(tasks.get(i));
        }
        return listTasks;
    }

    public ArrayList<Subtask> getAllSubtask() {
        ArrayList<Subtask> listTasks = new ArrayList<>();
        for (int i : subtasks.keySet()) {
            listTasks.add(subtasks.get(i));
        }
        return listTasks;
    }

    public ArrayList<Epic> getAllEpic() {
        ArrayList<Epic> listTasks = new ArrayList<>();
        for (int i : epics.keySet()) {
            listTasks.add(epics.get(i));
        }
        return listTasks;
    }

    public void updateTask (Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void updateSubtask (Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())){
            subtasks.put(subtask.getId(), subtask);
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
