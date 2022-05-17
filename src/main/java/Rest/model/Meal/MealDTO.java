package Rest.model.Meal;

import java.util.List;

public class MealDTO {


    public String mealName;
    public String id;

    public MealDTO() {
    }

    public MealDTO(String id,String mealName) {
        this.mealName = mealName;
        this.id = id;
    }
}
