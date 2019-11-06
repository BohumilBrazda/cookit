package cz.brazda.cookit.client.vaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import cz.brazda.cookit.SimpleRecipe;
import cz.brazda.cookit.client.vaadin.view.services.RecipeSessionService;
import cz.brazda.cookit.common.Unit;
import cz.brazda.cookit.repository.entity.*;
import cz.brazda.cookit.repository.entity.Image;
import cz.brazda.cookit.repository.entity.exceptions.MealNotFound;
import cz.brazda.cookit.repository.entity.exceptions.RecipeNotFound;
import cz.brazda.cookit.repository.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@HtmlImport("styles/shared-styles.html")
@Route("cookit/RecipeForm")
@Component
@UIScope
@Transactional
@Theme(value = Lumo.class)
public class CreateRecipeView extends FormLayout implements BeforeEnterObserver, BeforeLeaveObserver {

    private RecipeItem selectedItem = null;
    private Author selectedAuthor = null;
    private Meal selectedMeal = null;
    private Recipe recipe = null;
    private SimpleRecipe recipeDto = null;

    private RecipeSessionService recipeSessionService;
    private RecipeItemService recipeItemService;
    private RecipeService recipeService;
    private AuthorService authorService;
    private UserEventService userEventService;
    private MealService mealService;
    private CategoryService categoryService;
    private IngredientService ingredientService;

    @Autowired
    private ImageService imageService;

    private List<RecipeItem> items;
    private List<Author> authors;
    private List<Meal> meals;
    private ListDataProvider<RecipeItem> dataProvider;
    private List<RecipeItem> addedRecipeItems;
    private ListDataProvider<RecipeItem> itemsDataProvider;
    private Dialog dialog;
    private Dialog pictureDialog;
    private final ListBox<RecipeItem> listBox;

    private void refreshInputs() {

        recipe = recipeSessionService.getRecipe();

        items = recipeItemService.findAll();
        authors = authorService.findAll();
        meals = mealService.findAll();

        Assert.notNull(recipeSessionService.getRecipe(), "Recipe cannot be null!");
        recipe = recipeService.fetchLazy(recipeSessionService.getRecipe().getId());
        recipeDto = recipeService.findById(recipe.getId(), SimpleRecipe.class);
        selectedAuthor = authors.get(0);

    }

    private void recreatedProvider() {
        dataProvider = new ListDataProvider<>(items);
        itemsDataProvider = new ListDataProvider(recipe.getItems());
        if(listBox != null){
            listBox.setDataProvider(itemsDataProvider);
        }
        itemsDataProvider.refreshAll();
    }

    public CreateRecipeView(@Autowired RecipeItemService recipeItemService, @Autowired RecipeService recipeService, @Autowired AuthorService authorService, @Autowired UserEventService userEventService, @Autowired MealService mealService, @Autowired RecipeSessionService recipeSessionService, @Autowired CategoryService categoryService, @Autowired IngredientService ingredientService) {

        this.recipeSessionService = recipeSessionService;
        this.recipeItemService = recipeItemService;
        this.recipeService = recipeService;
        this.authorService = authorService;
        this.userEventService = userEventService;
        this.mealService = mealService;
        this.categoryService = categoryService;
        this.ingredientService = ingredientService;
        this.imageService = imageService;


        refreshInputs();

        createNewItemDialog(recipeItemService, recipeService, ingredientService);
        createPictureDialog(recipeService);
        HorizontalLayout hl = new HorizontalLayout();
        VerticalLayout vl1 = new VerticalLayout();
        VerticalLayout vl2 = new VerticalLayout();
        addedRecipeItems = new ArrayList<>();

        recreatedProvider();
        getStyle().set("marginLeft", "10.px");

        H5 title = new H5(("Ingredience pro recept " + recipe.getName()));

        listBox = new ListBox<>();
        listBox.setDataProvider(itemsDataProvider);
        listBox.setRenderer(new ComponentRenderer<>(item -> {
            HorizontalLayout itemLayout = new HorizontalLayout();
            H5 name = new H5(item.getName());
            Label description = new Label(item.getDescription());
            VerticalLayout nameLayout = new VerticalLayout(name, description);

            Label amount = new Label(item.getAmount().toString());
            Label units = new Label(item.getUnit().toString());

            itemLayout.add(nameLayout, amount,units);

            return itemLayout;
        }));
        listBox.setWidthFull();
        Button addButton = new Button("Add Item..");
        addButton.addClickListener(event -> {
            dialog.open();
        });

        Button addPicture = new Button("Add Picture..");
        addPicture.addClickListener(event -> {
            pictureDialog.open();
        });

        Button saveRecipe = new Button("Save recipe");
        saveRecipe.addClickListener(event -> {
            saveRecipe();
            navigateToMainView();

        });
        Button back = new Button("back to Recipes");
        back.addClickListener(event -> {
            navigateToMainView();
        });
        vl1.add(title, listBox);
        vl2.add(addButton,addPicture, saveRecipe, back);

        hl.add(vl1, vl2);
        add(hl);
    }

    private void savePicture(InputStream inputStream) {

        try {

            Image image = new Image();

            byte[] arrayPic = IOUtils.toByteArray(inputStream);

                Meal meal = recipe.getMeal();
                imageService.storeImage(meal, arrayPic);
                List<Image> images = mealService.fetchLazyImages(meal.getId()).getPictures();
                images.clear();
                images.add(image);
                try {
                    mealService.update(meal);
                } catch (MealNotFound mealNotFound) {
                    mealNotFound.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    private void createPictureDialog(@Autowired RecipeService recipeService){

        pictureDialog = new Dialog();

        VerticalLayout vl = new VerticalLayout();


        HorizontalLayout hl = new HorizontalLayout();
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");

        upload.addSucceededListener(event -> {
            savePicture(buffer.getInputStream(event.getFileName()));
        });

        hl.add(upload);

        HorizontalLayout buttons = new HorizontalLayout();

        Label messageLabel = new Label();
        VerticalLayout dummy = new VerticalLayout();

        Button confirmButton = new Button("Confirm", event -> {
            messageLabel.setText("Confirmed!");

            try {
                recipe = recipeService.update(recipe);
            } catch (RecipeNotFound recipeNotFound) {
                recipeNotFound.printStackTrace();
            }
            pictureDialog.close();

        });
        Button cancelButton = new Button("Cancel", event -> {
            messageLabel.setText("Cancelled...");
            pictureDialog.close();
        });
        buttons.add(dummy, confirmButton, cancelButton);
        vl.add(hl, buttons);

        pictureDialog.add(vl);

        pictureDialog.setWidth("600px");
        pictureDialog.setHeight("400px");
        //pictureDialog.setHeightFull();
        //pictureDialog.setWidthFull();


    }
    private void createNewItemDialog(@Autowired RecipeItemService recipeItemService, @Autowired RecipeService recipeService, @Autowired IngredientService ingredientService) {

        ListDataProvider<Ingredient> ingredientDataProvider = new ListDataProvider<>(ingredientService.findAll());
        ListDataProvider<Unit> unitDataProvider = new ListDataProvider<>(Arrays.asList(Unit.values()));

        dialog = new Dialog();

        VerticalLayout vl = new VerticalLayout();

        ComboBox<Ingredient> ingredients = new ComboBox<>("Ingredients");
        ingredients.setItemLabelGenerator(Ingredient::getName);

        ingredients.setDataProvider(ingredientDataProvider);


        HorizontalLayout hl = new HorizontalLayout();
        TextField amount = new TextField("Amount");

        ComboBox<Unit> unitComboBox = new ComboBox<>("Units");
        unitComboBox.setDataProvider(unitDataProvider);


        hl.add(amount, unitComboBox);

        TextField itemDescription = new TextField("Description");
        vl.add(ingredients, hl, itemDescription);

        HorizontalLayout buttons = new HorizontalLayout();

        Label messageLabel = new Label();

        Button confirmButton = new Button("Confirm", event -> {
            messageLabel.setText("Confirmed!");

            RecipeItem newItem = new RecipeItem();
            newItem.setAmount(Double.valueOf(amount.getValue()));
            newItem.setDescription(itemDescription.getValue());
            newItem.setUnit(unitComboBox.getValue());
            newItem.setRecipe(recipe);
            newItem.setIngredient(ingredients.getValue());
            newItem.setName(ingredients.getValue().getName());
            RecipeItem savedItem = recipeItemService.create(newItem);
            recipe.getItems().add(savedItem);
            try {
                recipe = recipeService.update(recipe);
            } catch (RecipeNotFound recipeNotFound) {
                recipeNotFound.printStackTrace();
            }
            dialog.close();
            recreatedProvider();
        });
        Button cancelButton = new Button("Cancel", event -> {
            messageLabel.setText("Cancelled...");
            dialog.close();
        });
        buttons.add(confirmButton, cancelButton);
        vl.add(buttons);
        dialog.add(vl);

//        dialog.setWidth("600px");
//        dialog.setHeight("400px");
        dialog.setHeightFull();
        dialog.setWidthFull();


    }

    @Transactional
    public void saveRecipe() {

        recipe.getItems().addAll(addedRecipeItems);
        UserEvent creation = new UserEvent(selectedAuthor, new Date());
        recipe.setEdited(this.userEventService.create(creation));
        recipe.setMeal(selectedMeal);
        try {
            this.recipeService.update(recipe);
        } catch (RecipeNotFound recipeNotFound) {
            recipeNotFound.printStackTrace();
        }
    }

    private void navigateToMainView() {
        getUI().ifPresent(ui -> ui.navigate(""));
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        refreshInputs();
        recreatedProvider();

    }

    @Override
    public void beforeLeave(BeforeLeaveEvent event) {
//        refreshInputs();
//        recreatedProvider();
    }
}
