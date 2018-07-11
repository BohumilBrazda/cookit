package client.repository.service.remote.rest.converters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cz.brazda.cookit.common.dto.MealDto;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.common.dto.UserEventDto;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RecipeNodeToDtoConverter extends AbstractConverter<ObjectNode, RecipeDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected RecipeDto convert(ObjectNode source) {
        if(source != null){

            UserEventDto created = modelMapper.map(source.get("created"), UserEventDto.class);
            UserEventDto edited = modelMapper.map(source.get("created"), UserEventDto.class);
            MealDto meal = modelMapper.map(source.get("meal"), MealDto.class);

            List<RecipeItemDto> recipeItemDtos = new ArrayList<>();
            Iterator<JsonNode> items = source.findValues("items").iterator();
            for(JsonNode node: items.next()){

                RecipeItemDto itemDto = modelMapper.map(node, RecipeItemDto.class);
                recipeItemDtos.add(itemDto);
            }

            RecipeDto recipeDto = new RecipeDto(source.get("id").longValue(), source.get("name").textValue(), source.get("numberOfPortion").intValue(), source.get("price").floatValue(), created, edited, meal);
            recipeDto.setItems(recipeItemDtos);
            return recipeDto;
        }
        return new RecipeDto();
    }


}