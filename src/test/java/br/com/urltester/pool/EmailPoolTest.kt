package br.com.urltester.pool

import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.EmailPool
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EmailPoolTest : ModelPoolTest(EmailPool().instance()) {

    override val QUANTITY = 10L
    override val RULEVALUE = "teste@teste.com"
    override val PARAM_NAME = "email"
    override val TYPE = ParamType.EMAIL

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
            Comparator.CONTAINS
        )


}