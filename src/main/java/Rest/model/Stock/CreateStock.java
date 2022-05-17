package Rest.model.Stock;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="stock")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateStock {

    @XmlElement
    private String name;
    @XmlElement
    private int type;
}
