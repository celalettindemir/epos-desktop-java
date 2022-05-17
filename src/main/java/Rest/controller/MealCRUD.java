package Rest.controller;
import Rest.ServiceConfiguration;
import Rest.model.Definitions.ServiceResponse;
import Rest.model.Meal.MealDTO;
import Rest.model.Meal.MealResponse;
import Rest.model.Meal.UpdateMeal;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
public class MealCRUD {

    public MealDTO saveMeal(String mealName) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());

            GenericType<ServiceResponse<MealResponse>> type = new GenericType<ServiceResponse<MealResponse>>() {};
            ServiceResponse<MealResponse> response=target.path("/meal/add").request(MediaType.APPLICATION_JSON).post(Entity.entity(mealName, MediaType.APPLICATION_JSON),type);

            //System.out.println("response: " + response.getEntity() + ",  status " + response.getStatus());
            //ServiceResponse<PlaceResponse> serviceResponse= response.readEntity(new GenericType<ServiceResponse<PlaceResponse>>(){});
            return new MealDTO(response.data.getId(),response.data.getMealName());
        } catch (RuntimeException e) {
            throw e;
        }  finally { if(client != null) client.close(); }
    }

    public MealDTO updateMeal(UpdateMeal meal) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());

            GenericType<ServiceResponse<MealResponse>> type = new GenericType<ServiceResponse<MealResponse>>() {};
            ServiceResponse<MealResponse> updated = target.path("/meal/update").request()
                    .put(Entity.entity(meal, MediaType.APPLICATION_JSON),type);

            return new MealDTO(updated.data.getId(),updated.data.getMealName());
        } catch(RuntimeException e) {
            throw e;
        } finally { if(client != null) client.close(); }
    }

    public void removeMeal(String id) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());
            target.path("/meal/deleteMealById/"+id).request().delete();
        } catch(RuntimeException e) {
            throw e;
        } finally { if(client != null) client.close(); }
    }

    public List<MealDTO> findAllMeal() throws RuntimeException {
        Client client = null;
        try {
            List<MealDTO> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());
            //List<PlaceResponse> object= ;
            /*Collection<PlaceResponse> readValues = new ObjectMapper().readValue(
                    , new TypeReference<Collection<PlaceResponse>>() { }
            );*/
            GenericType<ServiceResponse<List<MealResponse>>> type = new GenericType<ServiceResponse<List<MealResponse>>>() {};

            ServiceResponse<List<MealResponse>> response= target.path("/meal/getMeals").request(MediaType.APPLICATION_JSON).get(type);

           for (MealResponse mealResponse:response.data) {
               models.add(new MealDTO(mealResponse.getId(),mealResponse.getMealName()));
            }
            return models;
        } catch(RuntimeException e) {
            throw e;
        }finally { if(client != null) client.close(); }
    }

}
