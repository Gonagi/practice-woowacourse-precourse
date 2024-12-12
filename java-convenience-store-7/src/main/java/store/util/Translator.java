package store.util;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Translator {
    public static LocalDate getCurLocalDate() {
        LocalDateTime now = DateTimes.now();
        return now.toLocalDate();
    }
}
