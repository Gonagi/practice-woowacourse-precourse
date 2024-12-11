package lotto.domain;

import java.util.Arrays;
import java.util.Objects;

public enum Result {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NOTHING(0, false, 0);

    private final int matchBasicCount;
    private final boolean matchBonus;
    private final int prizeMoney;

    Result(final int matchBasicCount, final boolean matchBonus, final int prizeMoney) {
        this.matchBasicCount = matchBasicCount;
        this.matchBonus = matchBonus;
        this.prizeMoney = prizeMoney;
    }

    public static Result of(final int matchBasicCount, final boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchingRank(matchBasicCount, matchBonus))
                .findFirst()
                .orElse(NOTHING);
    }

    private boolean matchingRank(final int matchBasicCount, final boolean matchBonus) {
        if (!this.matchBonus) {
            return Objects.equals(this.matchBasicCount, matchBasicCount);
        }
        return Objects.equals(this.matchBasicCount, matchBasicCount) &&
                Objects.equals(this.matchBonus, matchBonus);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
