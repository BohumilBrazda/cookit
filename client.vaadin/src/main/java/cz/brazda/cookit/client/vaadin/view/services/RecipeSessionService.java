package cz.brazda.cookit.client.vaadin.view.services;

import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import cz.brazda.cookit.repository.entity.Recipe;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@VaadinSessionScope
public class RecipeSessionService {

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    private Recipe recipe;



}
