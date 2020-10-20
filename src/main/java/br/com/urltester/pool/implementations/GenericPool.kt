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


    override fun compare(value1: String, value2: String, comparator: Comparator): Boolean {
        var numericValue1 = value1.length.toDouble()
        var numericValue2 = value2.length.toDouble()

        if (Utils.isNumeric(value1)) {
            numericValue1 = value1.toDouble()
            numericValue2 = value2.toDouble()
        }

        return when (comparator) {
            Comparator.EQUALS -> value1 == value2
            Comparator.DIFFERENT -> value1 != value2
            Comparator.GREATER_THAN -> numericValue1 > numericValue2
            Comparator.LESS_THAN -> numericValue2 < numericValue2
            Comparator.GREATER_THAN_EQUALS -> numericValue1 >= numericValue2
            Comparator.LESS_THAN_EQUALS -> numericValue1 <= numericValue2
            Comparator.STARTS_WITH -> value1.startsWith(value2)
            Comparator.ENDS_WITH -> value1.endsWith(value2)
            Comparator.CONTAINS -> value1.contains(value2)
            Comparator.NOT_CONTAINS -> !value1.contains(value2)
        }
    }


}