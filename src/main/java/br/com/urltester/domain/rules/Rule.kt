package br.com.urltester.domain.rules

import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.TestExecution
import br.com.urltester.pool.model.PoolFactory


data class Rule(
        val param: Param,

        val comparator: Comparator = Comparator.EQUALS,

        val value: String,

        val isNull: Boolean = false,

        val expectedResponse: Long = 200L
) {

    fun match(testExecution: TestExecution): Boolean {
        val matchParam = testExecution.params.filter { it.param == this.param }
        val poll = PoolFactory.getPoll(this.param)
        matchParam.forEach {
            if (poll.compare(it.value!!, this.value, comparator)) {
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        return "Rule(param=$param, comparator=$comparator, value='$value', isNull=$isNull, expectedResponse=$expectedResponse)"
    }


}