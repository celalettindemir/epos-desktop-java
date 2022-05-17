package Rest.model.Table;

public class TableDTO {

    public String TableName;
    public String PlaceId;

    public TableDTO() {
    }

    public TableDTO(String id, String tableName) {
        this.id = id;
        this.tableName = tableName;
    }

    public String id;
    public String tableName;

}
