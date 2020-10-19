package br.com.urltester

import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.ParamValue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CartesianTest {

    @Test
    fun generateCartesian() {
        val list1 = listOf("A", "B", "C", "D").map { value ->
            ParamValue(
                    param = Param(name = "letter", type = "String"),
                    value = value.toString())
        }
        val list2 = listOf("1", "2", "3").map { value ->
            ParamValue(
                    param = Param(name = "number", type = "String"),
                    value = value.toString())
        }

        val list3 = listOf("+", "-", "*", "/").map { value ->
            ParamValue(
                    param = Param(name = "operation", type = "String"),
                    value = value.toString())
        }


        val params = listOf(list1, list2, list3)
        var mainSet = params.first().map { listOf(it) }


        for (n in 1 until params.size) {
            mainSet = findCart(mainSet, params[n])
        }

        println("SIZE: " + mainSet.size)

        for (n in mainSet) {
            for (item in n) {
                print("${item.value}-")
            }
            println()
        }


    }

    open fun findCart(arr1: List<List<ParamValue>>, arr2: List<ParamValue>): List<List<ParamValue>> {
        val result = mutableListOf<List<ParamValue>>()
        for (element1 in arr1) {
            for (element2 in arr2) {
                val newList = element1.toMutableList()
                newList.add(element2)
                result.add(newList)
            }
        }
        return result
    }

}