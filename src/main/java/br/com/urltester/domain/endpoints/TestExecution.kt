package br.com.urltester.domain.endpoints

import br.com.urltester.domain.rules.RuleTestExecution
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.time.LocalDateTime

data class TestExecution(
    val testConfig: TestConfig,

    val date: LocalDateTime = LocalDateTime.now(),

    var response: Long? = null,

    var expectedResponse: Long = 200L,

    val params: List<ParamValue> = listOf(),

    var rules: List<RuleTestExecution> = listOf()
) {

    fun isCorrect(): Boolean {
        return expectedResponse == response
    }

    fun toURL(): String {
        var url = testConfig.endpoint.url

        //  Path parameters
        params.asSequence().filter { it.param.apiParamType == ApiParamType.PATH }.forEach {
            url = url.replace("{${it.param.name}}", it.value ?: "")
        }

        //Query Parameters
        val queryParameters = params.filter { it.param.apiParamType == ApiParamType.QUERY }
        if (queryParameters.isNotEmpty()) {
            val queryParameterString =
                queryParameters.joinToString(separator = "&", prefix = "?") { "${it.param.name}=${it.value}" }
            url += queryParameterString
        }

        return url
    }

    fun printAsFailed() {
        print(this.toURL())
        println(" | Expected Response was $expectedResponse but real was $response")
    }

    fun printAsCorrect() {
        print(this.toURL())
        println(" | Expected Response was $expectedResponse")
    }


    fun header(): HttpHeaders {
        val paramsHeaders = params.filter { it.param.apiParamType == ApiParamType.HEADER }

        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.contentType = MediaType.APPLICATION_JSON
        paramsHeaders.forEach {
            headers[it.param.name] = it.value
        }

        return headers
    }


}