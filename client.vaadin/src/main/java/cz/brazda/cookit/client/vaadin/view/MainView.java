package cz.brazda.cookit.client.vaadin.view;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import cz.brazda.cookit.client.vaadin.view.services.RecipeSessionService;
import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.service.MealService;
import cz.brazda.cookit.repository.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;


//@CssImport("styles/shared-styles.html")
@Route(value = "")
@Component
@UIScope
@VaadinSessionScope
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
public class MainView extends AppLayout {

    public MainView(@Autowired RecipeService recipeService, @Autowired RecipeSessionService recipeSessionService, @Autowired MealService mealService) {


        ListDataProvider<Recipe> dataProvider = new ListDataProvider(recipeService.findAll());

        TextField find = new TextField();
        find.setTitle("find");
        find.setClearButtonVisible(false);
        find.getElement().getStyle().set("margin-right", "10px").set("width", "auto");

        Button loginToProfile = new Button("Login");
        loginToProfile.getElement().getStyle().set("margin-right", "20px").set("width", "auto");
        Button createProfile = new Button("Create Profile");
        createProfile.getElement().getStyle().set("margin-right", "10px").set("width", "200px");
        //createProfile.getElement().getStyle().get("clearfix");
        VerticalLayout gap = new VerticalLayout();
//        HorizontalLayout navbarLeft = new HorizontalLayout();
//        navbarLeft.add(new DrawerToggle(), new H1("CookIT  "));
//        navbarLeft.setAlignItems(FlexComponent.Alignment.STRETCH);
//        navbarLeft.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        Label gapLabel = new Label();
        gap.add(gapLabel);
        HorizontalLayout navbarRight = new HorizontalLayout();
//        navbarRight.setWidth("100%");
//        navbarRight.setHeight("150px");
        //navbarRight.setAlignItems(FlexComponent.Alignment.END);
        //navbarRight.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        //navbarRight.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        navbarRight.setSpacing(true);
        navbarRight.setPadding(false);
        navbarRight.setMargin(false);
        navbarRight.setBoxSizing(BoxSizing.CONTENT_BOX);
        //navbarRight.getStyle().set("padding-left", "10px");
        navbarRight.getElement().getStyle().set("margin-right", "20px");
//        navbarRight.setAlignSelf(FlexComponent.Alignment.START, find);
        //navbarRight.setAlignSelf(FlexComponent.Alignment.END, loginToProfile);
        //navbarRight.setAlignSelf(FlexComponent.Alignment.END, createProfile);
        H1 cookitL = new H1("CookIT  ");
        cookitL.getElement().getStyle().set("margin-left", "10px").set("width", "auto");
        addToNavbar(new H1("CookIT  "),gap, find, loginToProfile, createProfile);
        addToNavbar(navbarRight);
        //setPrimarySection(AppLayout.Section.NAVBAR);

        //addToNavbar(navbarRight);

        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setFlexGrowForEnclosedTabs(1);

        Tab recipes = new Tab("Recipes");

        Tab about = new Tab("About");
        tabs.add(recipes, about);


        Div page1 = new Div();
        Div page2 = new Div();
        page1.setVisible(true);
        page2.setVisible(false);
        FormLayout rec = new FormLayout();
        ListBox<Recipe> listBox = new ListBox<>();
        listBox.setDataProvider(dataProvider);
        listBox.setRenderer(new ComponentRenderer<>(item -> {
            HorizontalLayout box = new HorizontalLayout();
            Meal meal = mealService.fetchLazyImages(item.getMeal().getId());
            StreamResource resource = null;
            Image img = null;
            if (meal.getPictures() != null && !meal.getPictures().isEmpty()) {
                resource = new StreamResource("fakeImageName.jpg", () -> new ByteArrayInputStream(meal.getPictures().get(0).getImage()));
                img = new Image(resource, "picture");
                img.setMaxWidth("200px");
            }
            VerticalLayout names = new VerticalLayout();
            H3 recipeName = new H3(item.getName());
            Label mealName = new Label(item.getMeal().getName());
            names.add(recipeName, mealName);
            Button button = new Button("Edit", event -> {
                listBox.getDataProvider().refreshItem(item);
                recipeSessionService.setRecipe(item);
                getUI().ifPresent(ui -> ui.navigate("cookit/RecipeForm"));
            });
            names.getStyle().set("background-color", "lightblue");
            if (img != null) {
                box.add(img);
            }
            box.add(names, button);

//            Div labels = new Div(recipeName, stock);
//            Div layout = new Div(labels, button);

            return box;
        }));
        listBox.addValueChangeListener(event -> {
            recipeSessionService.setRecipe(event.getValue());
        });

        rec.add(listBox);
        rec.setVisible(true);

        addToDrawer(tabs);
        setContent(rec);

        Map<Tab, com.vaadin.flow.component.Component> tabsToPages = new HashMap<>();
        tabsToPages.put(recipes, page1);
        tabsToPages.put(about, page2);


//        //Div pages = new Div(page1, page2);
//
//        Set<com.vaadin.flow.component.Component> pagesShown = Stream.of(page1)
//                .collect(Collectors.toSet());
//
//        tabs.addSelectedChangeListener(event -> {
//            pagesShown.forEach(page -> page.setVisible(false));
//            pagesShown.clear();
//            com.vaadin.flow.component.Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
//            selectedPage.setVisible(true);
//            pagesShown.add(selectedPage);
//        });

    }
}
