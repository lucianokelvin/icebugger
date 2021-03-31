package br.com.urltester.domain.endpoints

import br.com.urltester.domain.rules.Rule
import br.com.urltester.domain.rules.RuleTestExecution
import br.com.urltester.exceptions.InvalidRuleException
import br.com.urltester.utils.CartesianPlan


data class TestConfig(
    val defaultResponse: Int = 200,

    val endpoint: Endpoint,

    var rules: MutableList<Rule> = mutableListOf(),

    val executions: List<TestExecution> = listOf()

) {

    private val RULE_REGEX = "((.*)(\\W)(.*))+".toRegex();

    fun addRule(rule: String, expectedResponse: Long): TestConfig {
        this.rules.add(toRule(rule, expectedResponse))
        return this
    }

    private fun toRule(rule: String, expectedResponse: Long): Rule {
        val matches = RULE_REGEX.find(rule)?.groupValues.orEmpty()

        if (matches.isEmpty()) {
            throw InvalidRuleException(rule)
        }

        val param = endpoint.params.find { it.name == matches[2] }
        val comparator = br.com.urltester.domain.rules.Comparator.values().find { it.symbol == matches[3] }
        val value = matches[4]

        return Rule(param = param!!, comparator = comparator!!, value = value, expectedResponse = expectedResponse)
    }


    fun generateTestsExecution(): List<TestExecution> {
        val paramValues = this.endpoint.params.map { param ->
            val quantity = if (param.fixed) {
                1L
            } else {
                5L
            }
            val paramRules = rules.filter { it.param.name == param.name }
            val randomList = param.getPool()
                .randomList(quantity = quantity, shouldHaveNullValue = param.nullable, rules = paramRules)

            randomList.map { value ->
                ParamValue(
                    param = param,
                    value = value.toString(),
                    isNull = value == null
                )
            }
        }

        val combinations = CartesianPlan.forParamValues(paramValues)

        return combinations.map {
            val testExecution = TestExecution(
                testConfig = this,
                params = it
            )
            val rulesMatches = this.rules.asSequence().filter { it.match(testExecution) }
                .map { RuleTestExecution(testExecution = testExecution, rule = it) }.toList()

            if (rulesMatches.isNotEmpty()) {
                testExecution.expectedResponse =
                    rulesMatches.maxBy { it.rule?.expectedResponse ?: 0L }?.rule?.expectedResponse ?: 200L
            }

            testExecution.rules = rulesMatches

            testExecution
        }

    }

    override fun toString(): String {
        return "TestConfig(defaultResponse=$defaultResponse)"
    }


}