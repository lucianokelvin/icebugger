package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel

open class IntegerPool(private val min: Int = Integer.MIN_VALUE, private val max: Int = Integer.MAX_VALUE) : PoolModel<Int>() {

    override fun getRandomValue(): Int {
        return (min..max).random()
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