package lotto.domain

class WinningLotto(private val lotto: Lotto, private val bonusNumber: LottoNumber) {
    fun match(lotto: Lotto): Rank {
        val matchCount = this.lotto.match(lotto)
        val matchBonus = lotto.contains(bonusNumber)
        return Rank.rank(matchCount, matchBonus)
    }
}
