package cz.brazda.cookit.client.vaadin.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import cz.brazda.cookit.repository.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@HtmlImport("styles/shared-styles.html")
@Route("cookit/UserForm")
@Component
@UIScope
@VaadinSessionScope
public class UserFormView extends FormLayout {

    private List<Author> authors;

    public UserFormView(@Autowired AuthorService authorService){
        authors = authorService.findAll();

        ListDataProvider<Author> dataProvider = new ListDataProvider<>(authors);

        Author author = new Author();
        TextField firstName = new TextField("First Name");
        TextField secondName = new TextField("Second Name");

        Binder<Author> binder = new Binder<>(Author.class);
        binder.setBean(author);
        binder.bind(firstName, "firstName");
        binder.bind(secondName, "secondName");


        Button add = new Button("Add..");
        Button remove = new Button("Remove..");

        Button back = new Button("Back..");
        back.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("")));
        add(firstName, secondName, add, remove,back);

        ListBox<Author> listBox = new ListBox<>();
        listBox.setDataProvider(dataProvider);
        //listBox.setItems(authorService.findAll());
        listBox.setRenderer(new ComponentRenderer<>(item -> {
            Label name = new Label("Name: " + item.getFirstName());
            Label stock = new Label("Second name: " + item.getSecondName());


            Div labels = new Div(name, stock);
            Div layout = new Div(labels);

            labels.getStyle().set("display", "flex")
                    .set("flexDirection", "column").set("marginRight", "10px");
            layout.getStyle().set("display", "flex")
                    .set("alignItems", "center");

            return layout;
        }));
        listBox.addValueChangeListener(event -> {
           if(event.getValue() instanceof Author ){
               binder.setBean(event.getValue());
           }
        });

        add.addClickListener(event->{
            Author a = binder.getBean();
            authorService.create(a);
            authors.clear();
            authors.addAll(authorService.findAll());
            dataProvider.refreshAll();
        });
        remove.addClickListener(event->{
            try {

                authorService.delete( binder.getBean().getId());
                authors.clear();
                authors.addAll(authorService.findAll());
                dataProvider.refreshAll();
            } catch (AuthorNotFound authorNotFound) {
                authorNotFound.printStackTrace();
            }
        });
        add(listBox);

    }

    private class AddAuthorListener implements ComponentEventListener<ClickEvent<Button>> {

        private Binder<Author> binder;
        private AuthorService authorService;
        private ListDataProvider<Author> dataProvider;

        public AddAuthorListener(Binder<Author> binder, AuthorService authorService, ListDataProvider<Author> dataProvider){
            this.binder = binder;
            this.authorService = authorService;
            this.dataProvider = dataProvider;
        }

        @Override
        public void onComponentEvent(ClickEvent<Button> event) {
            Author a = binder.getBean();
            authorService.create(a);
            authors.clear();
            authors.addAll(authorService.findAll());
            dataProvider.refreshAll();
        }
    }
}
