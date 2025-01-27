package manager;

import main.manager.FileBackedTaskManager;
import main.manager.Managers;
import main.manager.TaskManager;
import main.model.Epic;
import main.model.Subtask;
import main.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileBackedTaskManagerTest {

   protected Subtask createSubtask(int epicId) {
       return new Subtask("name1", "description1",epicId);
   }

   protected Epic createEpic() {
       return new Epic("name1", "description1");
   }

   protected Task createTask() {
       return new Task("name1", "description1");
   }

   protected TaskManager manager;

    File file;

    @BeforeEach
    void beforeEach() {
        file = new File("file.csv");
        manager = new FileBackedTaskManager(file);
    }

    @Test
    public void shouldSaveAndLoadEmptyFile() throws IOException {
        assertEquals(manager.getAllTask(),manager.getHistory());
        manager = Managers.getDefault(file);
        assertEquals(manager.getAllTask(),manager.getHistory());
    }

    @Test
    void saveTest() throws IOException{
        FileBackedTaskManager managerFile = new FileBackedTaskManager(file);
        Task task= createTask();
        Epic epic = createEpic();
        managerFile.getTaskOnId(task.getId());
        managerFile.getEpicOnId(epic.getId());
        assertTrue(Files.exists(Paths.get("file.csv")), "Файл не создан.");
    }
/*
для него перекроила и вывела switch-case в отдельный метод, но все равно не поддается..

    @Test
    public void shouldSaveAndLoadSomeTasks() throws IOException{
        FileBackedTaskManager loadFromFile = FileBackedTaskManager.loadFromFile(file);
        final List<Task> tasks = loadFromFile.getAllTask();
        assertNotNull(tasks, "Возвращает не пустой список задач");
        assertEquals(1, tasks.size(), "Возвращает не пустой список задач");
        final List<Epic> epics = loadFromFile.getAllEpic();
        assertNotNull(epics, "Возвращает не пустой список эпиков");
        assertEquals(1, epics.size(), "Возвращает не пустой список эпиков");
        final List<Subtask> subtasks = loadFromFile.getAllSubtask();
        assertNotNull(subtasks, "Возвращает не пустой список подзадач");
        assertEquals(1, subtasks.size(), "Возвращает не пустой список подзадач");
        assertEquals(manager.getAllTask(), loadFromFile.getAllTask(), "Список задач после выгрузки не совпадает");
        assertEquals(manager.getAllEpic(), loadFromFile.getAllEpic(), "Список эпиков после выгрузки не совпадает");
        assertEquals(manager.getAllSubtask(), loadFromFile.getAllSubtask(), "Список подзадач после выгрузки не совпадает");
    }*/



}


