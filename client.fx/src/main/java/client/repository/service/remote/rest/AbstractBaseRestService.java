package client.repository.service.remote.rest;

import client.repository.model.Entity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.brazda.cookit.common.dto.EntityDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import javax.json.JsonArray;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bohumil Brázda on 24.5.2017.
 */
public abstract class AbstractBaseRestService<U extends Entity, V extends EntityDto> implements RestRemoteService<U>{

    private ModelMapper modelMapper;
    private Client client;

    private final WebTarget webTarget;

    protected abstract String getURIString();

    AbstractBaseRestService(ModelMapper modelMapper, Client client) {
        this.modelMapper = modelMapper;
        this.client = client;

        webTarget = client.target(UriBuilder.fromUri(getURIString()).build());
    }

    List<U> findAllEntities(Converter<V, U> converter, Class<V> entityDtoClass, Class<U> entityClass) throws IOException {
        modelMapper.addConverter(converter);
        return findAllEntities(entityDtoClass, entityClass);
    }
    List<U> findAllEntities(Class<V> entityDtoClass, Class<U> entityClass) throws IOException {
        JsonArray jsonArray = webTarget.request().get(JsonArray.class);
        List<U> entities = new ArrayList<>();
        entities.addAll(convertToEntities(jsonArray, entityDtoClass, entityClass));
        return entities;
    }

    U createEntity(U entity, Class<V> entityDtoClass, Class<U> entityClass) throws IOException {
        V entityDto = convertToDto(entity, entityDtoClass);
        javax.ws.rs.client.Entity<V> wsEntity = javax.ws.rs.client.Entity.entity(entityDto, MediaType.APPLICATION_JSON);
        JsonValue ja = webTarget.request().post(wsEntity, JsonStructure.class);
        return convertDtoToEntity(ja, entityDtoClass, entityClass);
    }

    void updateEntity(U entity, Class<V> entityDtoClass){
        V entityDto = convertToDto(entity,entityDtoClass);
        javax.ws.rs.client.Entity<V> wsEntity = javax.ws.rs.client.Entity.entity(entityDto, MediaType.APPLICATION_JSON);
        webTarget.path("/" ).path(entity.getId().toString()).request().put(wsEntity, JsonValue.class);
    }


    private V convertToDto(U entity, Class<V> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }


    protected List<V> convertToDtos(List<U> entities, Class<V> dtoClass) {
        List<V> dtos = new ArrayList<>();
        for (U entity : entities) {
            dtos.add(convertToDto(entity, dtoClass));
        }
        return dtos;
    }

    private U convertDtoToEntity(JsonValue jsonValue, Class<V> dtoClass, Class<U> entityClass) throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(jsonValue.toString());
        return convertDtoToEntity(jsonNode, dtoClass, entityClass);
    }

    private U convertDtoToEntity(JsonNode jsonNode, Class<V> dtoClass, Class<U> entityClass) {
        V dto = modelMapper.map(jsonNode, dtoClass);
        return modelMapper.map(dto, entityClass);
    }

    private List<U> convertToEntities(JsonStructure jsonStructure, Class<V> dtoClass, Class<U> entityClass) throws IOException {
        List<U> entities = new ArrayList<>();
        for (JsonValue jsonValue : jsonStructure.asJsonArray()) {
            entities.add(convertDtoToEntity(jsonValue, dtoClass, entityClass));
        }
        return entities;
    }
}
