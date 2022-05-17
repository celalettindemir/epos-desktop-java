package Rest.model.Meal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="meal")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateMeal {
    @XmlElement(name="mealName")
    private String mealName;

    public CreateMeal() {
    }

    public CreateMeal(String mealName) {
        this.mealName = mealName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }


}
