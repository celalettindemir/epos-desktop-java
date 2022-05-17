package Rest.model.Category;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="productCategory")
@XmlAccessorType(XmlAccessType.FIELD)

public class CreateCategory {

    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "image")
    private String image;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "parentId")
    private String parentId;
    @XmlElement(name = "textTip")
    private String textTip;

    public CreateCategory(String id, String image, String name, String parentId, String textTip) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.parentId = parentId;
        this.textTip = textTip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTextTip() {
        return textTip;
    }

    public void setTextTip(String textTip) {
        this.textTip = textTip;
    }

    public CreateCategory() {
    }

}
