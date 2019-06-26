package client.fx.application;

import client.fx.application.splash.CookitSplashScreen;
import client.fx.config.ClientApplConfig;
import client.fx.views.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by Bohumil Brázda on 21.5.2017.
 */
@SpringBootApplication
@Import({ClientApplConfig.class})
public class CookitFX extends AbstractJavaFxApplicationSupport {


    public static void main(String[] args) {
        launchApp(CookitFX.class, MainView.class, new CookitSplashScreen(),args);
    }
}
