package manager;


import main.manager.InMemoryHistoryManager;
import main.manager.InMemoryTaskManager;
import main.manager.TaskManager;
import main.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Random;

import static main.status.StatusTask.NEW;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    protected Subtask addSubtask(int epicId) {
        return new Subtask("name1", "description1",NEW, epicId);
    };
    protected Epic addEpic() {
        return new Epic("name1", "description1", NEW);
    };
    protected Task addTask() {
        return new Task("name1", "description1", NEW);
    };
    protected TaskManager manager;
    @BeforeEach
    void beforeEach() {
        manager = new InMemoryTaskManager();
    }

    @Test
    public void shouldBeInfinityTasksInHistory() { //done
        int random = new Random().nextInt(1000);
        for (int i = 0; i < random; i++) {
            manager.getTaskOnId(manager.createTask(addTask()));
        }
        assertEquals(random, manager.getHistory().size());
    }

    @Test
    public void shouldBeAddTasksInHistory() { //done
        Task task = addTask();
        manager.getTaskOnId(manager.createTask(task));
        assertEquals(List.of(task),manager.getHistory());
    }

    @Test
    public void shouldBeAddEpicInHistory() { //done
        Epic epic = addEpic();
        manager.getEpicOnId(manager.createEpic(epic));
        assertEquals(List.of(epic),manager.getHistory());
    }

    @Test
    public void shouldBeRemoveTasksInHistory() { //done
        Task task = addTask();
        manager.deleteTaskOnId(task.getId());
        System.out.println(manager.getHistory());
        for (Task elem : manager.getHistory()){
            assertEquals(null,elem);
        }
    }

    @Test
    public void shouldBeRemoveEpicInHistory() {
        Epic epic = addEpic();
        int epicId = manager.createEpic(epic);
        manager.deleteEpicOnId(epicId);
        assertEquals(true,manager.getHistory().isEmpty());
    }


}
