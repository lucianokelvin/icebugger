package br.com.urltester.pool

import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.CharPool
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CharPoolTest : ModelPoolTest(CharPool().instance()) {

    override val QUANTITY = 10L
    override val RULEVALUE = "a"
    override val PARAM_NAME = "code"
    override val TYPE = ParamType.CHAR

    override val testComparators =
        listOf(
            Comparator.EQUALS,
            Comparator.DIFFERENT
        )


}