package br.com.urltester.domain.endpoints

import br.com.urltester.pool.implementations.GenericPool
import br.com.urltester.pool.model.PoolModel
import java.util.*

data class Param(
    val name: String,

    val type: ParamType = ParamType.OTHER,

    val fixed: Boolean = false,

    val nullable: Boolean = false,

    val poolValues: PoolValues? = null,

    val apiParamType: ApiParamType = ApiParamType.QUERY
) {

    companion object {

        fun header(name: String, pool: String): Param {
            val pollObject = PoolValues(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return Param(
                name = name,
                type = ParamType.OTHER,
                nullable = false,
                fixed = true,
                apiParamType = ApiParamType.HEADER,
                poolValues = pollObject
            )
        }

        fun path(
            name: String,
            type: ParamType = ParamType.OTHER,
            nullable: Boolean = false,
            fixed: Boolean = false,
            pool: String
        ): Param {
            val pollObject = PoolValues(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return Param(
                name = name,
                type = type,
                nullable = nullable,
                fixed = fixed,
                apiParamType = ApiParamType.PATH,
                poolValues = pollObject
            )
        }

        fun query(
            name: String,
            type: ParamType = ParamType.OTHER,
            nullable: Boolean = false,
            fixed: Boolean = false,
            pool: String = ""
        ): Param {
            val pollObject = PoolValues(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return Param(
                name = name,
                type = type,
                nullable = nullable,
                fixed = fixed,
                apiParamType = ApiParamType.QUERY,
                poolValues = pollObject
            )
        }

    }


    fun getPool(): PoolModel<*> {
        if (poolValues?.value != null && type == ParamType.OTHER) {
            return GenericPool(poolValues.value).instance()
        }

        return (this.type.poolModel.constructors.first().call() as PoolModel<*>).instance()
    }

    override fun toString(): String {
        return "Param(name='$name', type='$type', fixed=$fixed, nullable=$nullable, pool=$poolValues)"
    }
}