package br.com.urltester.pool

import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.rules.Comparator
import br.com.urltester.domain.rules.Rule
import br.com.urltester.pool.model.PoolModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test

abstract class ModelPoolTest(open val pool: PoolModel<*>) {

    abstract val QUANTITY: Long
    abstract val RULEVALUE: String
    abstract val PARAM_NAME: String
    abstract val TYPE: ParamType

    abstract val testComparators: List<Comparator>

    @Test
    open fun should_create_a_quantity_without_null() {

        val values = pool.randomList(quantity = QUANTITY)

        assertThat(values.size).isEqualTo(QUANTITY)
        assertThat(values).doesNotContain(null)
    }

    @Test
    open fun should_create_with_null() {
        val values = pool.randomList(shouldHaveNullValue = true)

        assertThat(values).contains(null)
    }


    @Test
    open fun should_create_a_with_rule_equal() {
        testComparator(Comparator.EQUALS)
    }

    @Test
    open fun should_create_a_with_rule_different() {
        testComparator(Comparator.DIFFERENT)
    }


    @Test
    open fun should_create_a_with_rule_less() {
        testComparator(Comparator.LESS_THAN)
    }

    @Test
    open fun should_create_a_with_rule_greater() {
        testComparator(Comparator.GREATER_THAN)
    }

    @Test
    open fun should_create_a_with_rule_less_or_equals() {
        testComparator(Comparator.LESS_THAN_EQUALS)
    }

    @Test
    open fun should_create_a_with_rule_greater_or_equals() {
        testComparator(Comparator.GREATER_THAN_EQUALS)
    }

    @Test
    open fun should_create_a_with_rule_starts_with() {
        testComparator(Comparator.STARTS_WITH)
    }

    @Test
    open fun should_create_a_with_rule_ends_with() {
        testComparator(Comparator.ENDS_WITH)
    }

    @Test
    open fun should_create_a_with_rule_contains() {
        testComparator(Comparator.CONTAINS)
    }

    private fun getRule(comparator: Comparator): Rule {
        val param = Param(name = PARAM_NAME, type = TYPE)
        return Rule(param = param, value = RULEVALUE, comparator = comparator)
    }

    private fun createValuesToRuleToComparator(comparator: Comparator): Boolean {
        val rule = getRule(comparator)

        val values = pool.randomList(rules = listOf(rule))

        return values.any { pool.compare(it.toString(), RULEVALUE, rule.comparator) }
    }


    private fun testComparator(comparator: Comparator) {
        assumeTrue(testComparators.contains(comparator))

        if (comparator in testComparators) {
            val containValue = createValuesToRuleToComparator(comparator)

            assertThat(containValue).isTrue()
        }
    }

}