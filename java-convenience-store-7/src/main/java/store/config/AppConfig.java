package store.config;

import static store.constants.Messages.INVALID_FILE_PATH;

import java.io.FileNotFoundException;
import store.controller.Controller;
import store.domain.Storage;
import store.domain.Store;
import store.view.InputView;
import store.view.OutputView;

public class AppConfig {
    private static final String PROMOTION_FILE_PATH = "java-convenience-store-7/src/main/resources/promotions.md";
    private static final String PRODUCT_FILE_PATH = "java-convenience-store-7/src/main/resources/products.md";
    private final Store store;
    private final InputView inputview;
    private final OutputView outputView;


    public AppConfig() {
        this.store = store();
        this.inputview = inputview();
        this.outputView = outputView();
    }

    public Controller controller() {
        return new Controller(store, inputview, outputView);
    }

    public InputView inputview() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public Storage storage() throws FileNotFoundException {
        return Storage.from(PRODUCT_FILE_PATH, PROMOTION_FILE_PATH);
    }

    public Store store() {
        try {
            return Store.from(storage());
        } catch (FileNotFoundException e) {
            System.out.println(INVALID_FILE_PATH.getErrorMessage());
        }
        return null;
    }
}
