package cz.brazda.cookit.rest.api.controllers;

import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.common.dto.EntityDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract parent for rest controllers includes set of default conversion methods
 * Created by Bohumil Brázda on 14.5.2017.
 */
abstract class AbstractController<U extends IdElement, V extends EntityDto> {

    @Autowired
    private ModelMapper modelMapper;


    V convertToDto(U entity, Class<V> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    V convertToDto(Converter<U, V> converter, U entity, Class<V> dtoClass){
        modelMapper.addConverter(converter);
        return convertToDto(entity, dtoClass);
    }

    U convertToEntity(V dto, Class<U> entityClass){
        return modelMapper.map(dto, entityClass);
    }

    U convertToEntity(Converter<V,U> converter, V dto, Class<U> entityClass){
        modelMapper.addConverter(converter);
        return convertToEntity(dto, entityClass);
    }

    List<V> convertToDtos(Converter<U, V> converter, List<U> entities, Class<V> dtoClass){
        modelMapper.addConverter(converter);
        return convertToDtos(entities, dtoClass);
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
