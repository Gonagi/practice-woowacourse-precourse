package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StoreTest {
    @Test
    void 로또_구입_정상_흐름() {
        Store store = new Store();
        Assertions.assertDoesNotThrow(() -> store.buyLottos(1000));
    }

    @ValueSource(ints = {0, 999})
    @ParameterizedTest(name = "구입 금액: {0}")
    void 구입금액은_1000원_이상이어야_합니다(int money) {
        Store store = new Store();
        Assertions.assertThrows(IllegalArgumentException.class, () -> store.buyLottos(money));
    }

    @Test
    void 구입금액은_1000원으로_떨어져야_합니다() {
        Store store = new Store();
        Assertions.assertThrows(IllegalArgumentException.class, () -> store.buyLottos(1500));
    }
}
