package main;

import main.manager.Manager;
import main.model.Epic;
import main.status.StatusTask;
import main.model.Subtask;
import main.model.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");


            Manager manager = new Manager();

            // Создание
            Task task1 = new Task("main.model.Task #1", "Task1 description", StatusTask.NEW);
            Task task2 = new Task("main.model.Task #2", "Task2 description", StatusTask.IN_PROGRESS);
            final int taskId1 = manager.createTask(task1);
            final int taskId2 = manager.createTask(task2);

            Epic epic1 = new Epic("main.model.Epic #1", "Epic1 description", StatusTask.NEW);
            Epic epic2 = new Epic("main.model.Epic #2", "Epic2 description",StatusTask.NEW);
            final int epicId1 = manager.createEpic(epic1);
            final int epicId2 = manager.createEpic(epic2);

            Subtask subtask1 = new Subtask("main.model.Subtask #1-1", "main.model.Subtask description", StatusTask.NEW, epicId1);
            Subtask subtask2 = new Subtask("main.model.Subtask #2-1", "main.model.Subtask description", StatusTask.NEW, epicId1);
            Subtask subtask3 = new Subtask("main.model.Subtask #3-2", "main.model.Subtask description", StatusTask.DONE, epicId2);
            final Integer subtaskIds1 = manager.createSubtask(subtask1);
            final Integer subtaskIds2 = manager.createSubtask(subtask2);
            final Integer subtaskIds3 = manager.createSubtask(subtask3);

        manager.getAllTask();
        manager.getAllSubtask();
        manager.getAllEpic();

            // Обновление
            final Task task = manager.getTaskOnId(taskId2);
            task.setStatus(StatusTask.DONE);
            manager.updateTask(task);
            System.out.println("CHANGE STATUS: Task2 IN_PROGRESS->DONE");
            System.out.println("Задачи:");
            for (Task t : manager.getAllTask()) {
                System.out.println(t);
            }

            Subtask subtask = manager.getSubtaskOnId(subtaskIds2);
            subtask.setStatus(StatusTask.DONE);
            manager.updateSubtask(subtask);
            System.out.println("CHANGE STATUS: Subtask2 NEW->DONE");
            subtask = manager.getSubtaskOnId(subtaskIds2);
            subtask.setStatus(StatusTask.NEW);
            manager.updateSubtask(subtask);
            System.out.println("CHANGE STATUS: Subtask3 DONE->NEW");
            System.out.println("Подзадачи:");
            for (Task t : manager.getAllSubtask()) {
                System.out.println(t);
            }

            System.out.println("Эпики:");
            for (Task e : manager.getAllEpic()) {
                System.out.println(e);
                for (Task t : manager.getSubtasksOnEpic(e.getId())) {
                    System.out.println("--> " + t);
                }
            }
            final Epic epic = manager.getEpicOnId(epicId1);
            epic.setStatus(StatusTask.NEW);
            manager.updateEpic(epic);
            System.out.println("CHANGE STATUS: Epic1 IN_PROGRESS->NEW");

            manager.getAllTask();
            manager.getAllSubtask();
            manager.getAllEpic();

            System.out.println("Эпики:");
            for (Task e : manager.getAllEpic()) {
                System.out.println(e);
                for (Task t : manager.getSubtasksOnEpic(e.getId())) {
                    System.out.println("--> " + t);
                }
            }

            // Удаление
            System.out.println("DELETE: Task1");
            manager.deleteTaskOnId(taskId1);
            System.out.println("DELETE: Epic1");
            manager.deleteEpicOnId(epicId1);

            manager.getAllTask();
            manager.getAllSubtask();
            manager.getAllEpic();
        }


    }
