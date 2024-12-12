package store;

import store.config.AppConfig;
import store.controller.Controller;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Controller controller = appConfig.controller();

        controller.run();
    }
}
