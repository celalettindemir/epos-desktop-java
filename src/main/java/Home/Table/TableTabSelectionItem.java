package Home.Table;

import Home.Table.TableTab.TableTab1Controller;

public class TableTabSelectionItem {
    private String ID;
    private int tabIndexId;
    private TableTab1Controller tableTab1Controller;

    public TableTabSelectionItem(String ID, int tabIndexId, TableTab1Controller tableTab1Controller) {
        this.ID = ID;
        this.tabIndexId = tabIndexId;
        this.tableTab1Controller = tableTab1Controller;
    }

    public TableTabSelectionItem(String ID, TableTab1Controller tableTab1Controller) {
        this.ID = ID;
        this.tableTab1Controller = tableTab1Controller;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public TableTab1Controller getTableTab1Controller() {
        return tableTab1Controller;
    }

    public void setTableTab1Controller(TableTab1Controller tableTab1Controller) {
        this.tableTab1Controller = tableTab1Controller;
    }
}
