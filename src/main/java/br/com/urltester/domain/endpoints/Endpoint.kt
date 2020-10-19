package br.com.urltester.domain.endpoints

import javax.validation.constraints.NotEmpty


data class Endpoint(
        val id: Long? = null,

        @NotEmpty
        val url: String,

        val method: HttpMethod,

        var params: MutableList<Param> = mutableListOf()
) {
    override fun toString(): String {
        return "Endpoint(id=$id, url='$url', method=$method)"
    }

    fun addParam(param: Param): Endpoint {
        params.add(param)
        return this
    }

}