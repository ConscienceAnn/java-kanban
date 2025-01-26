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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void shouldSaveAndLoadSomeTasks() throws IOException{
        Task task = createTask();
        Task task1 = createTask();
        manager.createTask(task);
        manager.createTask(task1);
        assertEquals(List.of(task,task1), manager.getAllTask());
        manager = Managers.getDefault(file);
       // assertEquals(List.of(task,task1), manager.getAllTask());
    }

}


