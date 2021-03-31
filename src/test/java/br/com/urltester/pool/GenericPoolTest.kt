package br.com.urltester.pool

import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.GenericPool
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GenericPoolTest : ModelPoolTest(GenericPool(value = "verde, amarelo, azul, branco").instance()) {

    override val QUANTITY = 4L
    override val RULEVALUE = "azul"
    override val PARAM_NAME = "color"
    override val TYPE = ParamType.OTHER

    override val testComparators =
        listOf(
            Comparator.EQUALS,
            Comparator.DIFFERENT,
            Comparator.GREATER_THAN,
            Comparator.GREATER_THAN_EQUALS,
            Comparator.LESS_THAN,
            Comparator.LESS_THAN_EQUALS,
            Comparator.STARTS_WITH,
            Comparator.ENDS_WITH,
            Comparator.CONTAINS,
            Comparator.NOT_CONTAINS
        )


}