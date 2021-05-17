package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.exceptions.InvalidValueException
import br.com.urltester.pool.model.PoolModel

open class IntegerPool(private val min: Int = Integer.MIN_VALUE, private val max: Int = Integer.MAX_VALUE) :
    PoolModel<Int>() {


    override fun defaultValues(): List<Int> {
        val values = mutableListOf<Int>()

        if (0 in (min..max)) {
            values.add(0)
        }

        if (min < 0) {
            values.add((min..minOf(max, -1)).random())
        }

        if (max > 0) {
            values.add((maxOf(min, 1)..max).random())
        }

        return values
    }

    override fun getRandomValue(): Int {
        return (min..max).random()
    }

    override fun convert(value: String?): Int {
        return value?.toIntOrNull() ?: throw InvalidValueException(value ?: "")
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

    override fun inc(value: String): Int {
        return value.toInt().inc()
    }

    override fun dec(value: String): Int {
        return value.toInt().dec()
    }

}