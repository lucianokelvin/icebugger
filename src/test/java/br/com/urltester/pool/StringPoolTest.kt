package br.com.urltester.pool

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.StringPool
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StringPoolTest : ModelPoolTest(StringPool().instance()) {

    override val QUANTITY = 10L
    override val RULEVALUE = "joao"
    override val PARAM_NAME = "name"
    override val TYPE = "String"

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