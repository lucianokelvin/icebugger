package br.com.urltester

import br.com.urltester.utils.isNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
open class UtilTest {

    @Test
    open fun `is number`() {
        assertThat("10".isNumber()).isTrue()
        assertThat("20.0".isNumber()).isTrue()
        assertThat("15".isNumber()).isTrue()
        assertThat("a".isNumber()).isFalse()
    }

}


