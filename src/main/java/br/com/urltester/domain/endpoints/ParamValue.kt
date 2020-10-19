package br.com.urltester.domain.endpoints


data class ParamValue(
        val param: Param,

        val value: String? = null,

        val isNull: Boolean = false
)