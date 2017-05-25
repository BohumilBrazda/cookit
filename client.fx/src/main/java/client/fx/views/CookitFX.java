package client.fx.views;

import java.io.IOException;

import client.fx.config.ClientApplConfig;
import client.fx.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Bohumil Brázda on 21.5.2017.
 */
public class CookitFX extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Main.fxml"));

        ApplicationContext context = new AnnotationConfigApplicationContext(ClientApplConfig.class);
        MainController controller = context.getBean(MainController.class);
        loader.setController(controller);

        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cookit application");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/fxml/img/ico.png")));
        stage.setResizable(true);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
