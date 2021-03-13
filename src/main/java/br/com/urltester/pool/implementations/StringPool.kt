package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel

open class StringPool(private val maxSize: Int = 10) : PoolModel<String>() {

    override fun getRandomValue(): String {
        val poll = CharPool()
        return (1..maxSize).map {
            poll.getRandomValue()
        }.joinToString("")
    }

    override fun convert(value: String?): String? {
        return value
    }

    override fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.EQUALS -> val1 == val2
            Comparator.DIFFERENT -> val1 != val2
            Comparator.GREATER_THAN -> val1.length > val2.length
            Comparator.LESS_THAN -> val1.length < val2.length
            Comparator.GREATER_THAN_EQUALS -> val1.length >= val2.length
            Comparator.LESS_THAN_EQUALS -> val1.length <= val2.length
            Comparator.STARTS_WITH -> val1.startsWith(val2)
            Comparator.ENDS_WITH -> val1.endsWith(val2)
            Comparator.CONTAINS -> val1.contains(val2)
            Comparator.NOT_CONTAINS -> !val1.contains(val2)
        }
    }

}