package manager;

import main.status.StatusTask;
import org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import main.model.*;

import main.manager.TaskManager;
import org.junit.jupiter.api.Test;

import static main.status.StatusTask.*;
import static org.junit.jupiter.api.Assertions.*;


public class TaskManagerTest <T extends TaskManager> {


    protected T manager;
    protected Subtask addSubtask(int epicId) {
        return new Subtask("name1", "description1",NEW, epicId);
    }
    protected Epic addEpic() {
        return new Epic("name1", "description1", NEW);
    }
    protected Task addTask() {
        return new Task("name1", "description1", NEW);
    }




}
