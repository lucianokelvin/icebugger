package br.com.urltester.domain.endpoints


data class PoolValues(
        val name: String,
        val description: String? = null,
        val value: String
)