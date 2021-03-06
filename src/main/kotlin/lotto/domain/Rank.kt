package lotto.domain

enum class Rank(
    val winningMoney: Money,
    val rankStrategy: (countOfMatch: Int, matchBonus: Boolean) -> Boolean
) {
    MISS(Money(0), { _, _ -> false }),
    FIFTH(Money(5_000), { countOfMatch, _ -> countOfMatch == 3 }),
    FOURTH(Money(50_000), { countOfMatch, _ -> countOfMatch == 4 }),
    THIRD(Money(1_500_000), { countOfMatch, matchBonus -> countOfMatch == 5 && !matchBonus }),
    SECOND(Money(30_000_000), { countOfMatch, matchBonus -> countOfMatch == 5 && matchBonus }),
    FIRST(Money(2_000_000_000), { countOfMatch, _ -> countOfMatch == 6 });

    fun prizeByCount(count: Int): Money = winningMoney * count

    companion object {
        fun rank(countOfMatch: Int, matchBonus: Boolean = false): Rank {
            return values().find { it.rankStrategy(countOfMatch, matchBonus) } ?: MISS
        }

        fun asList() = values().filter { it != MISS }

        fun getMatchCount(rank: Rank) = when (rank) {
            FIRST -> 6
            SECOND, THIRD -> 5
            FOURTH -> 4
            FIFTH -> 3
            else -> 0
        }
    }
}
