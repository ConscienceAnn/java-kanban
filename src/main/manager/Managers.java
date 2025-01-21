package main.manager;

import java.io.File;

public class Managers {

    private Managers() {
    }

    /*public static TaskManager getDefault() { //удаляем??
        return new InMemoryTaskManager();
    }*/

    public static TaskManager getDefault(File file) {
        return new FileBackedTaskManager(file);
    }

    public static HistoryManager getDefaultHistory() {
          return new InMemoryHistoryManager();
    }
//
}
