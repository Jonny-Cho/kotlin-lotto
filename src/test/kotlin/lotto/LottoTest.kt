package lotto

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import stringAddCalculator.LottoNumber

internal class LottoTest {

    @Test
    fun `중복된 숫자로 Lotto를 만들 수 없다`() {
        assertThatThrownBy { Lotto(1, 2, 3, 4, 5, 5) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Lotto.DUPLICATE_MESSAGE)
    }

    @Test
    fun `6개의 숫자가 아니라면 Lotto를 만들 수 없다`() {
        assertThatThrownBy { Lotto(1, 2, 3, 4, 5) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Lotto.INVALID_SIZE_MESSAGE)
        assertThatThrownBy { Lotto(1, 2, 3, 4, 5, 6, 7) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Lotto.INVALID_SIZE_MESSAGE)
    }

    @Test
    fun `7개 숫자를 넣었지만 하나가 중복인경우 `() {
        assertThatThrownBy { Lotto(1, 2, 3, 4, 5, 6, 6) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(Lotto.INVALID_SIZE_MESSAGE)
    }

    @Test
    fun `중복되지 않은 6개의 숫자로 Lotto를 만들 수 있다`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val expect = List(6) { i -> LottoNumber(i + 1) }.toSet()
        assertThat(lotto.lottoNumbers).isEqualTo(expect)
    }
}
