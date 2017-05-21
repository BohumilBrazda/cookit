package cz.brazda.cookit.rest.api.controllers;

import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.common.dto.AbstractDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by virtual on 14.5.2017.
 */
public class BaseController<U extends IdElement, V extends AbstractDto> {

    @Autowired
    private ModelMapper modelMapper;


    protected V convertToDto(U entity, Class<V> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    protected U convertToEntity(V dto, Class<U> entityClass){
        return modelMapper.map(dto, entityClass);
    }

    protected List<V> convertToDtos(List<U> entities, Class<V> dtoClass){
        List<V> dtos = new ArrayList<>();
        for (U entity:entities) {
            dtos.add(convertToDto(entity, dtoClass));
        }
        return dtos;
    }

    protected List<U> convertToEntities(List<V> dtos, Class<U> entityClass){
        List<U> entities = new ArrayList<>();
        for (V dto :dtos) {
            entities.add(convertToEntity(dto, entityClass));
        }
        return entities;
    }

}
