package store.domain;

public class Store {
    private Receipt receipt;
    private final Storage storage;

    private Store(final Storage storage) {
        this.receipt = new Receipt();
        this.storage = storage;
    }

    public static Store from(final Storage storage) {
        return new Store(storage);
    }


    public Receipt getReceipt() {
        return receipt;
    }

    public Storage getStorage() {
        receipt = new Receipt();
        return storage;
    }
}
