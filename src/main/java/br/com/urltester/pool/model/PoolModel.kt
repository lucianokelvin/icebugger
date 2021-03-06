package br.com.urltester.pool.model

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.domain.rules.Rule
import br.com.urltester.exceptions.InvalidComparatorException

@Suppress("UNCHECKED_CAST")
abstract class PoolModel<T>(var values: List<T> = listOf()) {

    open fun getRandomValue(): T {
        return values.random()
    }


    open fun defaultValues(): List<T> {
        return listOf()
    }

    open fun getRandomValueDiff(different: String? = null): T {
        var ruleValue = getRandomValue()

        different?.let {
            while (ruleValue == convert(it)) {
                ruleValue = getRandomValue()
            }
        }

        return ruleValue
    }

    open fun convert(value: String?): T? {
        return value as T
    }

    open fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.EQUALS -> convert(val1) == convert(val2)
            Comparator.DIFFERENT -> convert(val1) != convert(val2)
            else -> throw InvalidComparatorException(comparator)
        }
    }

    fun randomList(quantity: Long = 5L, shouldHaveNullValue: Boolean = false, rules: List<Rule> = listOf()): List<T?> {
        val list = valuesToRules(rules).toMutableList()

        val poolLimit = if (values.isEmpty()) {
            Integer.MAX_VALUE
        } else {
            values.size
        }

        val quantityToGenerate = minOf(poolLimit, (quantity.toInt() - list.size))
//        Fill the list to exactly quantity
        if (list.size < quantityToGenerate) {
            (1..(quantityToGenerate)).forEach {
//                Do not repeat random values
                var randomValue: T?
                do {
                    randomValue = this.getRandomValue()
                } while (list.contains(randomValue) && list.size < quantityToGenerate)

                list.add(randomValue)
            }
        }

//        Put a null value if necessary
        if (shouldHaveNullValue && !list.contains(null)) {
            list[list.size - 1] = null
        }

        list.addAll(defaultValues())
        return list.distinct()
    }

    private fun <T> getStringToRule(rule: Rule): T {
        return if (listOf(Comparator.ENDS_WITH, Comparator.CONTAINS).contains(rule.comparator)) {
            getStringWithSuffix(rule)
        } else {
            getStringWithPrefix(rule)
        } as T

    }

    private fun getStringWithPrefix(rule: Rule): String {
        val stringValue = getRandomValue().toString()
        return rule.value + stringValue.substring(rule.value.length, stringValue.length)
    }

    private fun getStringWithSuffix(rule: Rule): String {
        val stringValue = getRandomValue().toString()
        return stringValue.substring(0, (stringValue.length) - rule.value.length) + rule.value
    }


    private fun valuesToRules(rules: List<Rule>): List<T?> {
        return rules.map {
            val ruleValue: T? = when (it.comparator) {
                Comparator.EQUALS -> {
                    convert(it.value)
                }
                Comparator.DIFFERENT -> {
                    getRandomValueDiff(it.value)
                }
                in listOf(Comparator.CONTAINS, Comparator.ENDS_WITH, Comparator.STARTS_WITH) -> {
                    getStringToRule<T>(it)
                }
                Comparator.GREATER_THAN, Comparator.GREATER_THAN_EQUALS -> {
                    inc(it.value)
                }
                Comparator.LESS_THAN, Comparator.LESS_THAN_EQUALS -> {
                    dec(it.value)
                }
                else -> {
                    getRandomValue()
                }
            }
            ruleValue
        }.distinct()
    }

    abstract fun inc(value: String): T

    abstract fun dec(value: String): T

}