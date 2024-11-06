package manager;


import main.manager.InMemoryHistoryManager;
import main.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static main.status.StatusTask.NEW;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void addInHistoryTasks() {
        Task task1 = new Task("Test addNewTask1", "Test addNewTask1 description", NEW);
        Task task2 = new Task("Test addNewTask2", "Test addNewTask2 description", NEW);
        historyManager.add(task1);
        historyManager.add(task2);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "Верное кол-во задач.");
        assertIterableEquals(List.of(task1,task2),historyManager.getHistory(), "Добавлена в конце.");
    }

}
