package br.com.urltester.domain.rules

import br.com.urltester.domain.endpoints.TestExecution

data class RuleTestExecution(
        val testExecution: TestExecution? = null,

        val rule: Rule? = null
)