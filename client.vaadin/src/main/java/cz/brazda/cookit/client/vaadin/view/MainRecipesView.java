package cz.brazda.cookit.client.vaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@HtmlImport("styles/shared-styles.html")
@Route("")
@Component
@UIScope
@VaadinSessionScope
public class MainRecipesView extends FormLayout {

    public MainRecipesView(){
//        ListDataProvider<Recipe> dataProvider = new ListDataProvider<>(recipeService.findAll());
//
//        ListBox<Recipe> listBox = new ListBox<>();
//        listBox.setDataProvider(dataProvider);
//        //listBox.setItems(authorService.findAll());
//        listBox.setRenderer(new ComponentRenderer<>(item -> {
//            Label name = new Label("Recipe: " + item.getName());
//            Label stock = new Label("Meal: " + item.getMeal().getName());
//
//
//            Div labels = new Div(name, stock);
//            Div layout = new Div(labels);
//
//            labels.getStyle().set("display", "flex")
//                    .set("flexDirection", "column").set("marginRight", "10px");
//            layout.getStyle().set("display", "flex")
//                    .set("alignItems", "center");
//
//            return layout;
//        }));
////        listBox.addValueChangeListener(event -> {
////            if(event.getValue() instanceof Recipe ){
////
////            }
////        });

        Button button = new Button("Add Recipe");
//        button.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("cookit/RecipeForm")));

        add(button);
    }
}
