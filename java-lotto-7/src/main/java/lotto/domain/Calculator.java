package lotto.domain;

import java.util.List;

public class Calculator {
    public static String calculateRateOfReturn(final List<Result> results) {
        long prizeSum = getPrizeSum(results);
        int size = results.size();
        return String.format("%.1f", (double) prizeSum / (size * 10));
    }

    private static long getPrizeSum(final List<Result> results) {
        return results.stream()
                .mapToLong(Result::getPrizeMoney)
                .sum();
    }
}
