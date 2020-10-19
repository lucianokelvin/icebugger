package br.com.urltester.domain.endpoints


data class Pool(
        val id: Long? = null,

        val name: String,

        val description: String? = null,
        val value: String
)