package cz.brazda.cookit.client.vaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import cz.brazda.cookit.repository.entity.*;
import cz.brazda.cookit.repository.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@HtmlImport("styles/shared-styles.html")
@Route("cookit/RecipeForm")
@Component
@UIScope
@VaadinSessionScope
public class CreateRecipeView extends FormLayout {

    private RecipeItem selectedItem = null;
    private Author selectedAuthor = null;
    private Meal selectedMeal = null;


    public CreateRecipeView(@Autowired RecipeItemService recipeItemService, @Autowired RecipeService recipeService, @Autowired AuthorService authorService, @Autowired UserEventService userEventService, @Autowired MealService mealService) {


        List<RecipeItem> items = recipeItemService.findAll();
        List<Author> authors = authorService.findAll();
        List<Meal> meals = mealService.findAll();

        ListDataProvider<RecipeItem> dataProvider = new ListDataProvider<>(recipeItemService.findAll());
        List<RecipeItem> addedRecipeItems = new ArrayList<>();

        Recipe recipe = new Recipe();
        TextField recipeName = new TextField("Recipe name");
        TextField numberOfPortion = new TextField("Number of portions");


        Binder<Recipe> binder = new Binder<>(Recipe.class);
        binder.setBean(recipe);
        binder.bind(recipeName, "name");
        //binder.bind(numberOfPortion, "portions");

        ComboBox<RecipeItem> recipeItemsCombo = new ComboBox<>();
        recipeItemsCombo.setItems(items);
        recipeItemsCombo.addValueChangeListener(event -> selectedItem = event.getValue());

        ComboBox<Author> authorsCombo = new ComboBox<>();
        authorsCombo.setItems(authors);
        authorsCombo.addValueChangeListener(event -> selectedAuthor = event.getValue());

        ComboBox<Meal> mealsCombo = new ComboBox<>();
        mealsCombo.setItems(meals);
        mealsCombo.addValueChangeListener(event -> selectedMeal = event.getValue());


        Button addButton = new Button("Add Item..");
        addButton.addClickListener(event -> {
            if (selectedItem != null) {
                addedRecipeItems.add(selectedItem);
            }
        });
        Button saveRecipe = new Button("Save recipe");
        saveRecipe.addClickListener(event -> {
            recipe.setItems(addedRecipeItems);
            UserEvent creation = new UserEvent(selectedAuthor, new Date());
            UserEvent savedEvent = userEventService.create(creation);
            recipe.setCreated(savedEvent);
            recipe.setEdited(savedEvent);
            recipe.setMeal(selectedMeal);
            recipeService.create(recipe);
            getUI().ifPresent(ui -> ui.navigate(""));

        });
        add(recipeName, numberOfPortion);
        add(recipeItemsCombo, authorsCombo, mealsCombo);
        add(addButton, saveRecipe);


    }
}
