package main.manager;

import main.model.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    public static int maxInList = 10;
    public static ArrayList<Task> listHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (listHistory.size() < maxInList){
            listHistory.add(task);
        } else {
            listHistory.remove(0);
            listHistory.add(task);
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return listHistory;
    }

}
