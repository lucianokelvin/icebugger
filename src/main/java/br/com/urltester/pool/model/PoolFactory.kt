package br.com.urltester.pool.model

import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.PoolType
import br.com.urltester.pool.implementations.*

class PoolFactory {

    companion object {
        private fun getPoll(type: PoolType, values: String = ""): PoolModel<*> {
            return when (type) {
                PoolType.CHAR -> CharPool().instance()
                PoolType.STRING -> StringPool().instance()
                PoolType.NATURAL -> NaturalPool().instance()
                PoolType.INTEGER -> IntegerPool().instance()
                PoolType.EMAIL -> EmailPool().instance()
                PoolType.OTHER -> GenericPool(value = values)
            }
        }

        fun getPoll(param: Param): PoolModel<*> {
            return getPoll(PoolType.convert(param), param.pool?.value ?: "")
        }

    }


}