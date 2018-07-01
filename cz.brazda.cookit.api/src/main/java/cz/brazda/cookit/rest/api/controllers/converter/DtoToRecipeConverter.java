package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.repository.entity.Category;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.RecipeItem;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoToRecipeConverter extends AbstractConverter<RecipeDto, Recipe> {

    @Autowired
    private DtoToUserEventConverter dtoToUserEventConverter;

    @Autowired
    private DtoToMealConverter dtoToMealConverter;

    @Autowired
    private DtoToRecipeItemConverter dtoToRecipeItemConverter;

    @Override
    protected Recipe convert(RecipeDto source) {

        return source == null ? null : new Recipe(source.getId(), source.getName(), source.getNumberOfPortion(), source.getPrice(), getRecipeItems(source), dtoToUserEventConverter.convert(source.getCreated()), dtoToUserEventConverter.convert(source.getEdited()), dtoToMealConverter.convert(source.getMeal()));
    }

    private List<RecipeItem> getRecipeItems(RecipeDto source) {
        if(source.getItems() != null && !source.getItems().isEmpty()){
            List<RecipeItem> items = new ArrayList<>();
            source.getItems().forEach(item->items.add(dtoToRecipeItemConverter.convert(item)));
            return items;
        }return null;
    }
}