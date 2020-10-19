package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel

class BooleanPool : PoolModel<Boolean>(listOf(true, false)) {

    override fun convert(value: String?): Boolean? {
        return value == "true"
    }

    override fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.EQUALS -> convert(val1) == convert(val2)
            Comparator.DIFFERENT -> convert(val1) != convert(val2)
            else -> false
        }
    }

}