package br.com.urltester.pool

import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.BooleanPool
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BooleanPoolTest : ModelPoolTest(BooleanPool().instance()) {

    override val QUANTITY = 10L
    override val PARAM_NAME = "isChild"
    override val RULEVALUE = "true"
    override val TYPE = ParamType.BOOLEAN

    override val testComparators =
        listOf(
            Comparator.EQUALS,
            Comparator.DIFFERENT
        )

}