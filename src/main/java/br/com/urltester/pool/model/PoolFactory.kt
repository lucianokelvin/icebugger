package br.com.urltester.pool.model

import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.PoolType
import br.com.urltester.pool.implementations.*

class PoolFactory {

    companion object {
        private fun getPoll(type: PoolType, values: String = ""): PoolModel<*> {
            return when (type) {
                PoolType.CHAR -> CharPool().new()
                PoolType.STRING -> StringPool().new()
                PoolType.NATURAL -> NaturalPool().new()
                PoolType.INTEGER -> IntegerPool().new()
                PoolType.EMAIL -> EmailPool().new()
                PoolType.OTHER -> GenericPool(value = values)
            }
        }

        fun getPoll(param: Param): PoolModel<*> {
            return getPoll(PoolType.convert(param), param.pool?.value ?: "")
        }

    }


}