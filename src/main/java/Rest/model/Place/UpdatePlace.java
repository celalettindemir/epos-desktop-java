package Rest.model.Place;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="place")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdatePlace {
    public UpdatePlace(String id, String placeName) {
        this.id = id;
        PlaceName = placeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }
    @XmlElement(name="id")
    String id;
    @XmlElement(name="PlaceName")
    String PlaceName;
}
