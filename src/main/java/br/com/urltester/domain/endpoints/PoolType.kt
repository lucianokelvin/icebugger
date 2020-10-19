package br.com.urltester.domain.endpoints

enum class PoolType {
    CHAR, STRING, NATURAL, INTEGER, EMAIL, OTHER;

    companion object {
        fun convert(param: Param): PoolType {
            return when {
                (param.name == "email") -> EMAIL
                (param.type == "char") -> CHAR
                (param.type in listOf("Integer", "Int", "Long", "long")) -> NATURAL
                (param.type == "String") -> STRING
                else -> OTHER
            }
        }
    }
}