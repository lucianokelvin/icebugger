package br.com.urltester.pool.model

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.domain.rules.Rule

@Suppress("UNCHECKED_CAST")
abstract class PoolModel<T>(var values: List<T> = listOf()) {

    private var poolModel: PoolModel<T>? = null

    open fun getRandomValue(): T {
        return values.random()
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
            else -> throw RuntimeException("Comparador inválido")
        }
    }

    open fun inc(value: String): String {
        value.toIntOrNull()?.let {
            return (it + 1).toString();
        }

        return value.plus("a")
    }

    open fun dec(value: String): String {
        value.toIntOrNull()?.let {
            return (it - 1).toString();
        }

        return value.dropLast(1)
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
                    (inc(it.value)) as T
                }
                Comparator.LESS_THAN, Comparator.LESS_THAN_EQUALS -> {
                    (dec(it.value)) as T
                }
                else -> {
                    getRandomValue()
                }
            }
            ruleValue
        }.distinct()
    }

}