package client.fx.application.splash;


import de.felixroske.jfxsupport.SplashScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by Bohumil Brázda on 4.6.2017.
 */
public class CookitSplashScreen extends SplashScreen {

    /**
     * Override this to create your own splash pane parent node.
     *
     * @return A standard image
     */
    public Parent getParent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/SplaschScreen.fxml"));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Customize if the splash screen should be visible at all.
     *
     * @return true by default
     */
    public boolean visible() {
        return true;
    }

}
