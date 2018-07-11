package client.repository.service.remote.rest;

import client.repository.model.Entity;
import client.repository.service.remote.rest.converters.UserEventNodeToDtoConverter;
import client.repository.service.remote.rest.converters.RecipeNodeToDtoConverter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.istack.internal.NotNull;
import cz.brazda.cookit.common.dto.EntityDto;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.UserEventDto;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.JsonArray;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bohumil Brázda on 24.5.2017.
 */
@Component
public abstract class AbstractBaseRestService<U extends Entity, V extends EntityDto> implements RestRemoteService<U>{

    private static final String PATH_DELIMITER = "/";


    private List<Converter> converters;
    private ModelMapper modelMapper;
    private Client client;

    @Autowired
    private ObjectMapper objectMapper;


    private final WebTarget webTarget;
    private PropertyMap<JsonNode, V> map;
    protected abstract String getURIString();

    @Autowired
    AbstractBaseRestService(List<Converter> converters, ModelMapper modelMapper, Client client) {
        this.converters = converters;
        this.modelMapper = modelMapper;
        this.client = client;

        converters.forEach((v)->modelMapper.addConverter(v));
        if (modelMapper.getTypeMap(ObjectNode.class,RecipeDto.class) == null){
            modelMapper.createTypeMap(ObjectNode.class, RecipeDto.class).setConverter(new RecipeNodeToDtoConverter());

        }
        if(modelMapper.getTypeMap(ObjectNode.class, UserEventDto.class) == null){
            modelMapper.createTypeMap(ObjectNode.class, UserEventDto.class).setConverter(new UserEventNodeToDtoConverter());
        }
        webTarget = client.target(UriBuilder.fromUri(getURIString()).build());
    }

    List<U> findAllEntities(PropertyMap<JsonNode, V> map, Class<V> entityDtoClass, Class<U> entityClass) throws IOException {
        this.map =map;
        return findAllEntities(entityDtoClass, entityClass);
    }
    List<U> findAllEntities(List<Converter> converters, Class<V> entityDtoClass, Class<U> entityClass) throws IOException {
        for (Converter converter: converters) {
            modelMapper.addConverter(converter);
        }
        return findAllEntities(entityDtoClass, entityClass);
    }
    List<U> findAllEntities(Class<V> entityDtoClass, Class<U> entityClass) throws IOException {
        JsonArray jsonArray = webTarget.request().get(JsonArray.class);
        List<U> entities = new ArrayList<>();
        entities.addAll(convertToEntities(jsonArray, entityDtoClass, entityClass));
        return entities;
    }
    U findEntity(Long id, Class<V> entityDtoClass, Class<U> entityClass) throws IOException {
        JsonValue jsonValue = webTarget.path(PATH_DELIMITER).path(id.toString()).request().get(JsonValue.class);
        return convertDtoToEntity(jsonValue, entityDtoClass, entityClass);
    }

    void deleteEntity(Long id) throws IOException {
        webTarget.path(PATH_DELIMITER).path(id.toString()).request().delete(JsonValue.class);
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
        webTarget.path(PATH_DELIMITER).path(entity.getId().toString()).request().put(wsEntity, JsonValue.class);
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

    private List<U> convertToEntities(@NotNull JsonStructure jsonStructure, Class<V> dtoClass, Class<U> entityClass) throws IOException {
        List<U> entities = new ArrayList<>();
        for (JsonValue jsonValue : jsonStructure.asJsonArray()) {
            entities.add(convertDtoToEntity(jsonValue, dtoClass, entityClass));
        }
        return entities;
    }

    private U convertDtoToEntity(@NotNull JsonValue jsonValue, Class<V> dtoClass, Class<U> entityClass) throws IOException {
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        JsonNode jsonNode = objectMapper.readTree(jsonValue.toString());
        return convertDtoToEntity(jsonNode, dtoClass, entityClass);
    }

    private U convertDtoToEntity(JsonNode jsonNode, Class<V> dtoClass, Class<U> entityClass) {
        V dto = modelMapper.map(jsonNode, dtoClass);
            return modelMapper.map(dto, entityClass);
        }
}
