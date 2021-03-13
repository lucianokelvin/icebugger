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

        private fun <T> cartesianPlan(mainList: List<List<T>>, newElements: List<T>): List<List<T>> {
            val result = mutableListOf<List<T>>()
            for (list in mainList) {
                for (newParam in newElements) {
                    val newList = list.toMutableList()
                    newList.add(newParam)
                    result.add(newList)
                }
            }
            return result
        }
    }
}