package Rest.model.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="table")
@XmlAccessorType(XmlAccessType.FIELD)

public class UpdateTable {
    @XmlElement(name="id")
    private String id;
    @XmlElement(name="TableName")
    private String TableName;
    @XmlElement(name="placeId")
    private String PlaceId;

    public UpdateTable() {
    }

    public UpdateTable(String id, String tableName) {
        this.id = id;
        TableName = tableName;
    }

    public UpdateTable(String id, String tableName, String placeId) {
        this.id = id;
        TableName = tableName;
        PlaceId = placeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getPlaceId() {
        return PlaceId;
    }

    public void setPlaceId(String placeId) {
        PlaceId = placeId;
    }
}
