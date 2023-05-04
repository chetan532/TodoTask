package manoj.cs.niu.edu;

public class TodoModel {

    private int id;
    private String name;
    private String selected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        selected = selected;
    }

    public TodoModel(int id, String name, String selected) {
        this.id = id;
        this.name = name;
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }
}

