package cz.brazda.cookit.rest.api.controllers;

import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.common.dto.AbstractDto;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.repository.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.V;

/**
 * Created by virtual on 14.5.2017.
 */
public abstract class AbstractController<U extends IdElement, V extends AbstractDto> {

    @Autowired
    protected ModelMapper modelMapper;

    public abstract V toDto(U entity);

    protected List<V> toDtos(List<U> entities){
        List<V> dtos = new ArrayList<>();
        for (U entity:entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
