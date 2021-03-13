package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel
import br.com.urltester.utils.Utils

class GenericPool(value: String) : PoolModel<String>() {

    init {
        if (value.isNotEmpty()) {
            this.values = value.split(",").map { it.trim() }
        }
    }

    override fun convert(value: String?): String? {
        return value
    }


    override fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        val (numericValue1, numericValue2) = if (Utils.isNumeric(val1)) {
            arrayOf(val1.toDouble(), val2.toDouble())
        } else {
            arrayOf(val1.length.toDouble(), val2.length.toDouble())
        }

        return when (comparator) {
            Comparator.EQUALS -> val1 == val2
            Comparator.DIFFERENT -> val1 != val2
            Comparator.GREATER_THAN -> numericValue1 > numericValue2
            Comparator.LESS_THAN -> numericValue1 < numericValue2
            Comparator.GREATER_THAN_EQUALS -> numericValue1 >= numericValue2
            Comparator.LESS_THAN_EQUALS -> numericValue1 <= numericValue2
            Comparator.STARTS_WITH -> val1.startsWith(val2)
            Comparator.ENDS_WITH -> val1.endsWith(val2)
            Comparator.CONTAINS -> val1.contains(val2)
            Comparator.NOT_CONTAINS -> !val1.contains(val2)
        }
    }


}