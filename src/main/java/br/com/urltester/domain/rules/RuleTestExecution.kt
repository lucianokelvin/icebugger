package br.com.urltester.domain.rules

import br.com.urltester.domain.endpoints.TestExecution


data class RuleTestExecution(
        val id: Long? = null,

        val testExecution: TestExecution? = null,

        val rule: Rule? = null
)