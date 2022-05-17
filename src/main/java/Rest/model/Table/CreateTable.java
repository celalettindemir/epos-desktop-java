package Rest.model.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="table")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateTable {
    @XmlElement(name="TableName")
    private String TableName;
    @XmlElement(name="placeId")
    private String PlaceId;

    public CreateTable() {
    }

    public CreateTable(String tableName, String placeId) {
        TableName = tableName;
        PlaceId = placeId;
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
