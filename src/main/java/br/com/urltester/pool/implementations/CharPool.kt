package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel

class CharPool : PoolModel<Char>(('a'..'z').toList()) {

    override fun convert(value: String?): Char? {
        return value?.get(0)
    }


    override fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.EQUALS -> convert(val1) == convert(val2)
            Comparator.DIFFERENT -> convert(val1) != convert(val2)
            else -> false
        }
    }

}