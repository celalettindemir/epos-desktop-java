package Home.Table;

public class TableSelectionItem {
    private String ID;
    private String name;

    public TableSelectionItem(String id, String name) {
        this.ID = id;
        this.name = name;
    }

    public String getID() { return ID; }
    public String getName() { return name; }
    public String setID(String id) { return ID=id; }
    public String setName(String name) { return this.name=name; }
}
