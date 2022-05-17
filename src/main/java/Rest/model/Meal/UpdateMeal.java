package Rest.model.Meal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="meal")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateMeal {

    @XmlElement(name="mealName")
    private String mealName;
    @XmlElement(name="id")
    private String id;

    public UpdateMeal(String mealName, String id) {
        this.mealName = mealName;
        this.id = id;
    }

    public UpdateMeal() {
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
