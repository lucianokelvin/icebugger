package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.exceptions.InvalidValueException
import br.com.urltester.pool.model.PoolModel
import kotlin.math.sign
import kotlin.random.Random

class DoublePool(private val min: Double = Double.MIN_VALUE, private val max: Double = Double.MAX_VALUE) :
    PoolModel<Double>() {

    override fun getRandomValue(): Double {
        return Random.nextDouble(min, max)
    }

    override fun defaultValues(): List<Double> {
        val values = mutableListOf<Double>()

        if (0.0 in (min..max)) {
            values.add(0.0)
        }

        if (min < 0) {
            values.add((Random.nextDouble(min, minOf(max, sign(1.0)))))
        }

        if (max > 0) {
            values.add((Random.nextDouble(maxOf(min, 1.0), max)))
        }

        return values
    }

    override fun convert(value: String?): Double {
        return value?.toDoubleOrNull() ?: throw InvalidValueException(value ?: "")
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

    override fun inc(value: String): Double {
        return value.toDouble().inc()
    }

    override fun dec(value: String): Double {
        return value.toDouble().dec()
    }
}