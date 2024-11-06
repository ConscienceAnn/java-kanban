package main.manager;

import main.model.Task;

import java.util.ArrayList;
import java.util.List;


public class InMemoryHistoryManager implements HistoryManager {
    public static int MAX_IN_LIST = 10;
    public static List<Task> listHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (listHistory.size() < MAX_IN_LIST){
            listHistory.add(task);
        } else {
            listHistory.remove(0);
            listHistory.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return listHistory;
    }

}
