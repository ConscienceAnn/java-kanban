import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds;


    public Epic(String name, String description, StatusTask status, ArrayList<Integer> subtaskIds) {
        super(name, description, status);
        this.subtaskIds = subtaskIds;
    }

    public Epic(String name, String description, int id, StatusTask status, ArrayList<Integer> subtaskIds) {
        super(name, description, id, status);
        this.subtaskIds = subtaskIds;
    }

    public Epic(String name, String description, StatusTask status) {
        super(name, description, status);
    }


    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(ArrayList<Integer> subtaskIds) {
        this.subtaskIds = subtaskIds;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", subtaskIDs=" + subtaskIds +
                ", status='" + getStatus() +
                '}';
    }

}
