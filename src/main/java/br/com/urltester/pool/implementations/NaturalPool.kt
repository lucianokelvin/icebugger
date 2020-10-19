package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel

class NaturalPool(private val max: Int = 100000) : PoolModel<Int>() {

    override fun getRandomValue(): Int {
        return (0..max).random()
    }

    override fun convert(value: String?): Int? {
        return value?.toIntOrNull()
    }

    override fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.EQUALS -> convert(val1) == convert(val2)
            Comparator.DIFFERENT -> convert(val1) != convert(val2)
            Comparator.GREATER_THAN -> convert(val1)!! > convert(val2)!!
            Comparator.LESS_THAN -> convert(val1)!! < convert(val2)!!
            Comparator.GREATER_THAN_EQUALS -> convert(val1)!! >= convert(val2)!!
            Comparator.LESS_THAN_EQUALS -> convert(val1)!! <= convert(val2)!!
            else -> false
        }
    }
}