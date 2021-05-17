package br.com.urltester.domain.endpoints.params

import br.com.urltester.domain.endpoints.ApiParamType
import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.endpoints.PoolValues
import java.util.*

data class PathParam(
    override var name: String,

    override var type: ParamType = ParamType.OTHER,

    override var fixed: Boolean = false,

    override var nullable: Boolean = false,

    override var poolValues: PoolValues? = null,

    override val apiParamType: ApiParamType = ApiParamType.PATH
) : Param {
    companion object {
        fun of(
            name: String,
            type: ParamType = ParamType.OTHER,
            nullable: Boolean = false,
            fixed: Boolean = false,
            pool: String
        ): Param {
            val poolObject = PoolValues(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return PathParam(
                name = name,
                type = type,
                nullable = nullable,
                fixed = fixed,
                poolValues = poolObject
            )
        }

    }
}