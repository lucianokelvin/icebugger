package br.com.urltester.pool.model

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.domain.rules.Rule

@Suppress("UNCHECKED_CAST")
abstract class PoolModel<T>(var values: List<T> = listOf()) {

    private var poolModel: PoolModel<T>? = null

    open fun getRandomValue(): T {
        return values.random()
    }

    open fun convert(value: String?): T? {
        return value as T
    }

    open fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.EQUALS -> convert(val1) == convert(val2)
            Comparator.DIFFERENT -> convert(val1) != convert(val2)
            else -> throw RuntimeException("Comparador inválido")
        }
    }

    open fun inc(val1: String): String {
        val1.toIntOrNull()?.let {
            return (it + 1).toString();
        }

        return val1 + "a"
    }

    open fun dec(val1: String): String {
        val1.toIntOrNull()?.let {
            return (it - 1).toString();
        }

        return val1.substring(0, val1.length - 2)
    }


    fun instance(): PoolModel<T> {
        if (poolModel == null) {
            poolModel = this
        }

        return poolModel!!
    }

    fun randomList(quantity: Long = 5L, shouldHaveNullValue: Boolean = false, rules: List<Rule> = listOf()): List<T?> {
        val list = valuesToRules(rules).toMutableList()

//        Fill the list to exactly quantity
        if (list.size < quantity) {
            list.addAll((1..(quantity - list.size)).map {
//                Do not repeat random values
                var randomValue: T?
                do {
                    randomValue = this.getRandomValue()
                } while (list.contains(randomValue) && (values.size > (quantity - list.size)))

                randomValue
            })
        }

//        Put a null value if necessary
        if (shouldHaveNullValue && !list.contains(null)) {
            list[list.size - 1] = null
        }

        return list
    }

    private fun <T> getStringToRule(rule: Rule): T {
        val stringValue = if (listOf(Comparator.ENDS_WITH, Comparator.CONTAINS).contains(rule.comparator)) {
            getStringWithSufix(rule)
        } else {
            getStringWithPrefix(rule)
        }

        return stringValue as T
    }

    private fun getStringWithPrefix(rule: Rule): String {
        var stringValue = getRandomValue().toString()
        stringValue = stringValue.substring(rule.value.length, stringValue.length)
        return rule.value + stringValue

    }

    private fun getStringWithSufix(rule: Rule): String {
        var stringValue = getRandomValue().toString()
        stringValue = stringValue.substring(0, (stringValue.length) - rule.value.length)
        stringValue += rule.value
        return stringValue
    }


    private fun valuesToRules(rules: List<Rule>): List<T?> {
        return rules.map {
            var ruleValue: T?

            when (it.comparator) {
                Comparator.EQUALS -> {
                    ruleValue = convert(it.value)
                }
                Comparator.DIFFERENT -> {
                    ruleValue = getRandomValue()
                    while (ruleValue == it.value) {
                        ruleValue = getRandomValue()
                    }
                }
                in listOf(Comparator.CONTAINS, Comparator.ENDS_WITH, Comparator.STARTS_WITH) -> {
                    ruleValue = getStringToRule<T>(it)
                }
                Comparator.GREATER_THAN, Comparator.GREATER_THAN_EQUALS -> {
                    ruleValue = (inc(it.value)) as T
                }
                Comparator.LESS_THAN, Comparator.LESS_THAN_EQUALS -> {
                    ruleValue = (dec(it.value)) as T
                }
                else -> {
                    ruleValue = getRandomValue()
                }
            }
            ruleValue
        }.distinct()
    }

}