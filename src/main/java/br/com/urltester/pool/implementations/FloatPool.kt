package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.exceptions.InvalidValueException
import br.com.urltester.pool.model.PoolModel
import kotlin.random.Random

class FloatPool(private val min: Float = Float.MIN_VALUE, private val max: Float = Float.MAX_VALUE) :
    PoolModel<Float>() {

    override fun getRandomValue(): Float {
        return Random.nextDouble(min.toDouble(), max.toDouble()).toFloat()
    }

    override fun convert(value: String?): Float {
        return value?.toFloatOrNull() ?: throw InvalidValueException(value ?: "")
    }

    override fun defaultValues(): List<Float> {
        val values = mutableListOf<Float>()

        if (0f in (min..max)) {
            values.add(0f)
        }

        if (min < 0) {
            values.add((Random.nextDouble(min.toDouble(), minOf(max, -1f).toDouble()).toFloat()))
        }

        if (max > 0) {
            values.add((Random.nextDouble(maxOf(min, 1f).toDouble(), max.toDouble()).toFloat()))
        }

        return values
    }

    override fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.GREATER_THAN -> convert(val1) > convert(val2)
            Comparator.LESS_THAN -> convert(val1) < convert(val2)
            Comparator.GREATER_THAN_EQUALS -> convert(val1) >= convert(val2)
            Comparator.LESS_THAN_EQUALS -> convert(val1) <= convert(val2)
            else -> super.compare(val1, val2, comparator)
        }
    }

    override fun inc(value: String): Float {
        return value.toFloat().inc()
    }

    override fun dec(value: String): Float {
        return value.toFloat().dec()
    }
}