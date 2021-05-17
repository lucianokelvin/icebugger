package br.com.urltester.domain.endpoints.params

import br.com.urltester.domain.endpoints.ApiParamType
import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.ParamType
import br.com.urltester.domain.endpoints.PoolValues
import java.util.*

class HeaderParam(
    override var name: String,

    override var type: ParamType = ParamType.OTHER,

    override var fixed: Boolean = false,

    override var nullable: Boolean = false,

    override var poolValues: PoolValues? = null,

    override val apiParamType: ApiParamType = ApiParamType.HEADER
) : Param {

    companion object {
        fun of(name: String, pool: String): HeaderParam {
            val poolObject = PoolValues(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return HeaderParam(
                name = name,
                type = ParamType.OTHER,
                nullable = false,
                fixed = true,
                poolValues = poolObject
            )
        }
    }


}