package br.com.urltester.pool

import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.NaturalPool
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NaturalPoolTest : ModelPoolTest(NaturalPool().instance()) {

    override val QUANTITY = 10L
    override val RULEVALUE = "30"
    override val PARAM_NAME = "val"
    override val TYPE = ParamType.NATURAL

    override val testComparators =
        listOf(
            Comparator.EQUALS,
            Comparator.DIFFERENT,
            Comparator.GREATER_THAN,
            Comparator.GREATER_THAN_EQUALS,
            Comparator.LESS_THAN,
            Comparator.LESS_THAN_EQUALS
        )
}