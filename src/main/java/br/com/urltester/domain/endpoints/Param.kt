package br.com.urltester.domain.endpoints

import java.util.*

data class Param(
        val id: Long? = null,

        val name: String,

        val type: String = "Other",

        val fixed: Boolean = false,

        val nullable: Boolean = false,

        val pool: Pool? = null,

        var endpoint: Endpoint? = null,

        val apiParamType: ApiParamType = ApiParamType.QUERY
) {

    companion object {

        fun header(name: String, endpoint: Endpoint? = null, pool: String): Param {
            val pollObject = Pool(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return Param(name = name, type = "Other", nullable = false, fixed = true, endpoint = endpoint, apiParamType = ApiParamType.HEADER, pool = pollObject)
        }

        fun path(name: String, type: String = "Other", nullable: Boolean = false, fixed: Boolean = false, pool: String): Param {
            val pollObject = Pool(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return Param(name = name, type = type, nullable = nullable, fixed = fixed, apiParamType = ApiParamType.PATH, pool = pollObject)
        }

        fun query(name: String, type: String = "Other", nullable: Boolean = false, fixed: Boolean = false, pool: String): Param {
            val pollObject = Pool(name = UUID.randomUUID().toString(), description = "None", value = pool)
            return Param(name = name, type = type, nullable = nullable, fixed = fixed, apiParamType = ApiParamType.QUERY, pool = pollObject)
        }

    }

    override fun toString(): String {
        return "Param(id=$id, name='$name', type='$type', fixed=$fixed, nullable=$nullable, pool=$pool)"
    }
}