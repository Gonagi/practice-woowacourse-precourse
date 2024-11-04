package lotto;

import lotto.config.AppConfig;
import lotto.controller.Controller;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        Controller controller = appConfig.controller();

        controller.run();
    }
}
