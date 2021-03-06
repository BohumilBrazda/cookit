package cz.brazda.cookit.rest.api.controllers;

import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.common.dto.EntityDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract parent for rest controllers includes set of default conversion methods
 * Created by Bohumil Brázda on 14.5.2017.
 */
@Component
abstract class AbstractController<U extends IdElement, V extends EntityDto> {

    private ModelMapper modelMapper;

    @Autowired
    public AbstractController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    V convertToDto(U entity, Class<V> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    U convertToEntity(V dto, Class<U> entityClass){
        return modelMapper.map(dto, entityClass);
    }

    List<V> convertToDtos(List<U> entities, Class<V> dtoClass){
        List<V> dtos = new ArrayList<>();
        for (U entity:entities) {
            dtos.add(convertToDto(entity, dtoClass));
        }
        return dtos;
    }

    List<U> convertToEntities(List<V> dtos, Class<U> entityClass){
        List<U> entities = new ArrayList<>();
        for (V dto :dtos) {
            entities.add(convertToEntity(dto, entityClass));
        }
        return entities;
    }

}
