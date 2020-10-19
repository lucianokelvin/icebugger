package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel
import kotlin.random.Random

class FloatPool(private val min: Float = Float.MIN_VALUE, private val max: Float = Float.MAX_VALUE) : PoolModel<Float>() {

    override fun getRandomValue(): Float {
        var value = Random.nextFloat()
        while (value < min || value > max) {
            value = Random.nextFloat()
        }
        return value
    }

    override fun convert(value: String?): Float? {
        return value?.toFloatOrNull()
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