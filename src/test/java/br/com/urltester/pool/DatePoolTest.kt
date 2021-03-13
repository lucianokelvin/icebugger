package br.com.urltester.pool

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.implementations.DatePool
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class DatePoolTest : ModelPoolTest(DatePool(LocalDate.of(1900, 1, 1), to = LocalDate.now()).instance()) {

    override val QUANTITY = 10L
    override val RULEVALUE = "2020-10-10"
    override val PARAM_NAME = "birthdate"
    override val TYPE = "Date"

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