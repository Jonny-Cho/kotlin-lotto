package lotto.view

import lotto.domain.LottoResult
import lotto.domain.LottoTicket
import lotto.domain.Money
import lotto.domain.Rank
import java.math.BigDecimal

object ResultView {
    private const val BUY_COUNT_SUFFIX = "개를 구매했습니다."
    private const val WINNING_STATISTICS = "당첨 통계"
    private const val WINNING_SEPARATOR = "---------"
    private const val COUNT_SUFFIX = "%d개"
    private const val SEPARATOR = "- "
    private const val TOTAL_RATE_PREFIX = "총 수익률은 "
    private const val TOTAL_RATE_SUFFIX = "입니다."
    private const val ACCORDANCE_SUFFIX = "%d개 일치"
    private const val BONUS_MESSAGE = ", 보너스 볼 일치"

    fun printLottos(lottoTicket: LottoTicket) {
        println("${lottoTicket.lottos.size}$BUY_COUNT_SUFFIX")
        printJoinToString(lottoTicket.lottos)
    }

    fun printResult(result: LottoResult, money: Money) {
        println(WINNING_STATISTICS)
        println(WINNING_SEPARATOR)
        Rank.asList().forEach {
            printRankResult(result.countByRank(it), it)
        }
        val totalRate = result.calculateTotalRate(money)
        printTotalRate(totalRate)
    }

    private fun printRankResult(count: Int, rank: Rank) {
        if (rank == Rank.MISS) return
        val countMatch = ACCORDANCE_SUFFIX.format(Rank.getMatchCount(rank))
        val winningMoney = "(%s)".format(rank.winningMoney)
        val countRank = COUNT_SUFFIX.format(count)
        val bonus = if (rank == Rank.SECOND) BONUS_MESSAGE else ""
        println("$countMatch$bonus$winningMoney$SEPARATOR$countRank")
    }

    private fun printTotalRate(totalRate: BigDecimal) {
        println("$TOTAL_RATE_PREFIX${totalRate}$TOTAL_RATE_SUFFIX")
    }

    private fun printJoinToString(lottos: List<Any>) {
        println(lottos.joinToString(""))
    }
}
