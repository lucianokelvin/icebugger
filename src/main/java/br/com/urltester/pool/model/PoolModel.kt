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

    fun new(): PoolModel<T> {
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
            list.removeAt(list.size - 1)
            list.add(null)
        }

        return list
    }


    private fun ruleToList(rule: Rule): T? {
        var ruleValue: T?

        when (rule.comparator) {
            Comparator.EQUALS -> {
                ruleValue = rule.value as T
            }
            in listOf(Comparator.CONTAINS, Comparator.ENDS_WITH) -> {
                var stringValue = getRandomValue().toString()
                stringValue = stringValue.substring(0, (stringValue.length) - rule.value.length)
                stringValue += rule.value
                ruleValue = stringValue as T
            }
            Comparator.STARTS_WITH -> {
                var stringValue = getRandomValue().toString()
                stringValue = stringValue.substring(rule.value.length, stringValue.length)
                stringValue = rule.value + stringValue
                ruleValue = stringValue as T
            }
            else -> {
                ruleValue = getRandomValue()
                while (ruleValue == rule.value) {
                    ruleValue = getRandomValue()
                }
            }
        }
        return ruleValue
    }

    private fun valuesToRules(rules: List<Rule>): List<T?> {
        return rules.map {
            var ruleValue: T?

            when (it.comparator) {
                Comparator.EQUALS -> {
                    ruleValue = it.value as T
                }
                Comparator.DIFFERENT -> {
                    ruleValue = getRandomValue()
                    while (ruleValue == it.value) {
                        ruleValue = getRandomValue()
                    }
                }
                in listOf(Comparator.CONTAINS, Comparator.ENDS_WITH) -> {
                    var stringValue = getRandomValue().toString()
                    stringValue = stringValue.substring(0, (stringValue.length) - it.value.length)
                    stringValue += it.value
                    ruleValue = stringValue as T
                }
                Comparator.STARTS_WITH -> {
                    var stringValue = getRandomValue().toString()
                    stringValue = stringValue.substring(it.value.length, stringValue.length)
                    stringValue = it.value + stringValue
                    ruleValue = stringValue as T
                }
                Comparator.GREATER_THAN, Comparator.GREATER_THAN_EQUALS -> {
                    ruleValue = (it.value.toInt() + 1) as T
                }
                Comparator.LESS_THAN, Comparator.LESS_THAN_EQUALS -> {
                    ruleValue = (it.value.toInt() - 1) as T
                }
                else -> {
                    ruleValue = getRandomValue()
                }
            }
            ruleValue
        }.distinct()
    }

}