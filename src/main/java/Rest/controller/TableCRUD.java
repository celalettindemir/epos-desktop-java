package Rest.controller;

import Rest.ServiceConfiguration;
import Rest.model.Definitions.ServiceResponse;
import Rest.model.Place.*;
import Rest.model.Table.CreateTable;
import Rest.model.Table.TableDTO;
import Rest.model.Table.TableResponse;
import Rest.model.Table.UpdateTable;
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


public class TableCRUD {

    public TableDTO saveTable(CreateTable createTable) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());

            GenericType<ServiceResponse<TableResponse>> type = new GenericType<ServiceResponse<TableResponse>>() {};
            ServiceResponse<TableResponse> response=target.path("/table/add").request(MediaType.APPLICATION_JSON).post(Entity.entity(createTable, MediaType.APPLICATION_JSON),type);

            //System.out.println("response: " + response.getEntity() + ",  status " + response.getStatus());
            //ServiceResponse<PlaceResponse> serviceResponse= response.readEntity(new GenericType<ServiceResponse<PlaceResponse>>(){});
            return new TableDTO(response.data.getId(),response.data.getName());
        } catch (RuntimeException e) {
            throw e;
        }  finally { if(client != null) client.close(); }
    }
    public TableDTO updateTable(UpdateTable table) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());

            GenericType<ServiceResponse<TableResponse>> type = new GenericType<ServiceResponse<TableResponse>>() {};
            ServiceResponse<TableResponse> updated = target.path("/table/update").request()
                    .put(Entity.entity(table, MediaType.APPLICATION_JSON),type);

            return new TableDTO(updated.data.getId(),updated.data.getName());
        } catch(RuntimeException e) {
            throw e;
        } finally { if(client != null) client.close(); }
    }

    public void removeTable(String id) throws RuntimeException {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());
            target.path("/table/deleteTableById/"+id).request().delete();
        } catch(RuntimeException e) {
            throw e;
        } finally { if(client != null) client.close(); }
    }

    public List<TableDTO> findAllTable() throws RuntimeException {
        Client client = null;
        try {
            List<TableDTO> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target(ServiceConfiguration.getBaseUri());
            //List<PlaceResponse> object= ;
            /*Collection<PlaceResponse> readValues = new ObjectMapper().readValue(
                    , new TypeReference<Collection<PlaceResponse>>() { }
            );*/
            GenericType<ServiceResponse<List<TableResponse>>> type = new GenericType<ServiceResponse<List<TableResponse>>>() {};

            ServiceResponse<List<TableResponse>> response= target.path("/table/getTables").request(MediaType.APPLICATION_JSON).get(type);
            //System.out.println("response: " + response.readEntity(new ServiceResponse<PlaceResponse>[]) + ",  status " + response.getStatus());
            for (TableResponse tableResponse:response.data) {
                models.add(new TableDTO(tableResponse.getId(),tableResponse.getName()));
            }
            return models;
        } catch(RuntimeException e) {
            throw e;
        }finally { if(client != null) client.close(); }
    }
}
