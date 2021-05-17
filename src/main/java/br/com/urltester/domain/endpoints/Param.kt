package br.com.urltester.domain.endpoints

interface Param {
    var name: String

    val type: ParamType

    val fixed: Boolean

    val nullable: Boolean

    val poolValues: PoolValues?

    val apiParamType: ApiParamType;

}
