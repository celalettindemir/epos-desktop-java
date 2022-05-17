package Rest.model.Meal;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;


@XmlRootElement(name="meal")
@XmlAccessorType(XmlAccessType.FIELD)
public class MealResponse {


    @XmlElement
    private String id;
    @XmlElement
    private Date createdDate;
    @XmlElement
    private String createdBy;
    @XmlElement
    private Date updatedDate;
    @XmlElement
    private String updatedBy;
    @XmlElement
    private Boolean isDeleted;
    @XmlElement
    private String mealName;

    @XmlElement
    @XmlTransient
    private ArrayList products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

   /* public ArrayList getProducts() {
        return products;
    }

    public void setProducts(ArrayList products) {
        this.products = products;
    }*/

    public MealResponse() {
    }

    public MealResponse(String id, Date createdDate, String createdBy, Date updatedDate, String updatedBy, Boolean isDeleted, String mealName) {
        this.id = id;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.isDeleted = isDeleted;
        this.mealName = mealName;
        //this.products = products;
    }
}
