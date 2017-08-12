package client.repository.service.remote.rest.converters;

import client.repository.model.Recipe;
import cz.brazda.cookit.common.dto.RecipeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Bohumil Brázda on 16.7.2017.
 */
public class RecipeModelMapper {
    @Autowired
    private ModelMapper modelMapper;

    public RecipeModelMapper(){
        modelMapper.addMappings(new PropertyMap<RecipeDto, Recipe>() {
            @Override
            protected void configure() {
                skip().setItems(null);
            }
        });
    }
}
