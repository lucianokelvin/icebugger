package br.com.urltester.domain.endpoints


data class Pool(
        val name: String,
        val description: String? = null,
        val value: String
)