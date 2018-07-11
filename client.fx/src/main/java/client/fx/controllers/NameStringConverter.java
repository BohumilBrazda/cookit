package client.fx.controllers;

import client.repository.model.Ingredient;
import cz.brazda.cookit.common.NameElement;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class NameStringConverter<T extends NameElement> extends StringConverter<T> {


    @Override
    public String toString(T object) {
        return object.getName();
    }

    @Override
    public T fromString(String string) {
        return null;
    }
}
