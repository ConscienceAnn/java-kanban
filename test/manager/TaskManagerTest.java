package manager;

import org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import main.model.*;

import main.manager.TaskManager;
import org.junit.jupiter.api.Test;

import static main.status.StatusTask.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TaskManagerTest <T extends TaskManager> {
        protected T taskManager;


    @Test
    void addNewTask() { //Название из ТЗ, но тут сразу несколько проверок и непонятно как впихнуть все в название
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        final int taskId = taskManager.createTask(task); // вылетаю с ошибкой, но не понимаю, что не так?

        final Task savedTask = taskManager.getTaskOnId(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final ArrayList<Task> tasks = taskManager.getAllTask();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    public void addNewEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        //final int epicId = taskManager.createEpic(epic);
        final Epic savedEpic = taskManager.getEpicOnId(epic.getId());

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final ArrayList<Epic> tasks = taskManager.getAllEpic();

        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(epic, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    public void createNewSubtask() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", NEW);
        Subtask subtask = new Subtask ( "Test addNewSubtask", "Test addNewSubtask description", NEW, epic.getId());
        final Subtask savedTask = taskManager.getSubtaskOnId(subtask.getId());

        assertNotNull(savedTask, "Задача не найдена.");
        assertNotNull(savedTask.getEpicId(), "Эпик-задача не найдена.");
        assertEquals(subtask, savedTask, "Задачи не совпадают.");

        final ArrayList<Subtask> tasks = taskManager.getAllSubtask();

        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(subtask, tasks.get(0), "Задачи не совпадают.");
    }

}
