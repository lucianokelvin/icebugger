package br.com.urltester.pool

import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.GenericPool
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GenericIntegerPoolTest : ModelPoolTest(GenericPool(value = "1,2,3,4,5,6,7,8,9,10")) {

    override val QUANTITY = 10L
    override val RULEVALUE = "5"
    override val PARAM_NAME = "id"
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