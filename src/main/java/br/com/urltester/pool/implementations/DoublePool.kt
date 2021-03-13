package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel
import kotlin.random.Random

class DoublePool(private val min: Double = Double.MIN_VALUE, private val max: Double = Double.MAX_VALUE) : PoolModel<Double>() {

    override fun getRandomValue(): Double {
        return Random.nextDouble(min, max)
    }

    override fun convert(value: String?): Double? {
        return value?.toDoubleOrNull()
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