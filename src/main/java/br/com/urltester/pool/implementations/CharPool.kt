package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.exceptions.InvalidValueException
import br.com.urltester.pool.model.PoolModel

class CharPool : PoolModel<Char>(('a'..'z').toList()) {

    override fun convert(value: String?): Char {
        return value?.get(0) ?: throw InvalidValueException(value ?: "")
    }

    override fun defaultValues(): List<Char> {
        val number = (0..9).random().toChar()
        val letter = ('a'..'z').random()

        return listOf(number, letter)
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

    override fun inc(value: String): Char {
        return convert(value).inc()
    }

    override fun dec(value: String): Char {
        return convert(value).dec()
    }


}