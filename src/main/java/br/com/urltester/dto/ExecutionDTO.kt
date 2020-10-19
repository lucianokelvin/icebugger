package br.com.urltester.dto

data class ExecutionDTO(
        val draw: Long,
        val recordsTotal: Long,
        val recordsFiltered: Long,
        val data: List<List<String>>)