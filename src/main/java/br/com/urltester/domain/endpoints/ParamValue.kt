package br.com.urltester.domain.endpoints


data class ParamValue(
        val id: Long? = null,

        val param: Param,

        val value: String? = null,

        val isNull: Boolean = false,

        var testExecution: TestExecution? = null
)