package br.com.urltester.utils

import br.com.urltester.domain.endpoints.ParamValue

class CartesianPlan {

    companion object {

        fun forParamValues(params: List<List<ParamValue>>): List<List<ParamValue>> {
            var mainSet = params.first().map { listOf(it) }

            for (n in 1 until params.size) {
                mainSet = cartesianPlan(mainSet, params[n])
            }

            return mainSet
        }

        private fun <T> cartesianPlan(mainSet: List<List<T>>, newSet: List<T>): List<List<T>> {
            val result = mutableListOf<List<T>>()
            for (element1 in mainSet) {
                for (element2 in newSet) {
                    val newList = element1.toMutableList()
                    newList.add(element2)
                    result.add(newList)
                }
            }
            return result
        }
    }
}