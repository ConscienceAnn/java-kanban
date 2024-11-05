package main.manager;

import main.model.Task;
import java.util.ArrayList;

public class Managers {

    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static ArrayList<Task> getDefaultHistory(){
        return new InMemoryHistoryManager().getHistory();
    }

}
