package cz.brazda.cookit;

import cz.brazda.cookit.common.IdElement;

import java.util.List;

public interface SimpleRecipe extends IdElement {

    public String getName();

    List<RecipeItemRs> getItems();



}
