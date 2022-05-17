package Rest.controller;

import Rest.ServiceConfiguration;
import Rest.model.Definitions.ServiceResponse;
import Rest.model.Place.*;
import Rest.model.Table.CreateTable;
import Rest.model.Table.TableDTO;
import Rest.model.Table.TableResponse;
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


public class PlaceCRUD {

    public PlaceDTO savePlace(String placeName) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());

            GenericType<ServiceResponse<PlaceResponse>> type = new GenericType<ServiceResponse<PlaceResponse>>() {};
            ServiceResponse<PlaceResponse> response=target.path("/place/add").request(MediaType.APPLICATION_JSON).post(Entity.entity(placeName, MediaType.APPLICATION_JSON),type);

            //System.out.println("response: " + response.getEntity() + ",  status " + response.getStatus());
            //ServiceResponse<PlaceResponse> serviceResponse= response.readEntity(new GenericType<ServiceResponse<PlaceResponse>>(){});
            return new PlaceDTO(response.data.getId(),response.data.getName());
        } catch (RuntimeException e) {
            throw e;
        }  finally { if(client != null) client.close(); }
    }
    public PlaceDTO updatePlace(UpdatePlace place) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());

            GenericType<ServiceResponse<PlaceResponse>> type = new GenericType<ServiceResponse<PlaceResponse>>() {};
            ServiceResponse<PlaceResponse> updated = target.path("/place/update").request()
                    .put(Entity.entity(place, MediaType.APPLICATION_JSON),type);

            return new PlaceDTO(updated.data.getId(),updated.data.getName());
        } catch(RuntimeException e) {
            throw e;
        } finally { if(client != null) client.close(); }
    }

    public void removePlace(String id) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());
            target.path("/place/deletePlaceById/"+id).request().delete();
        } catch(RuntimeException e) {
            throw e;
        } finally { if(client != null) client.close(); }
    }
    public List<PlaceDTO> findAllPlace() throws RuntimeException {
        Client client = null;
        try {
            List<PlaceDTO> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());
            //List<PlaceResponse> object= ;
            /*Collection<PlaceResponse> readValues = new ObjectMapper().readValue(
                    , new TypeReference<Collection<PlaceResponse>>() { }
            );*/
            GenericType<ServiceResponse<List<PlaceResponse>>> type = new GenericType<ServiceResponse<List<PlaceResponse>>>() {};

            ServiceResponse<List<PlaceResponse>> response= target.path("/place/getPlaces").request(MediaType.APPLICATION_JSON).get(type);
            //System.out.println("response: " + response.readEntity(new ServiceResponse<PlaceResponse>[]) + ",  status " + response.getStatus());
            for (PlaceResponse placeResponse:response.data) {
                List<TableDTO> list=new ArrayList<>();
                for (TableResponse tableResponse:placeResponse.getTables()){
                    TableDTO tableDTO=new TableDTO();
                    tableDTO.id=tableResponse.getId();
                    tableDTO.tableName=tableResponse.getName();
                    tableDTO.PlaceId=placeResponse.getId();
                    list.add(tableDTO);
                }
                models.add(new PlaceDTO(placeResponse.getId(),placeResponse.getName(),list));
            }
            return models;
        } catch(RuntimeException e) {
            throw e;
        }finally { if(client != null) client.close(); }
    }
}
