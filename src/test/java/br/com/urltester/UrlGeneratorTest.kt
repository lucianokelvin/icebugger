package br.com.urltester

import br.com.urltester.domain.endpoints.Endpoint
import br.com.urltester.domain.endpoints.HttpMethod
import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.endpoints.TestConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UrlGeneratorTest {

    @Test
    fun `should create urls with query param`() {
        val paramName = "codes"
        val values = "br,ar,us,col,ger,rus,can,esp,it,par,uru"
        val url = "https://paises.com/search/"

        val endpoint = Endpoint(url = url, method = HttpMethod.GET).addParam(Param.query(paramName, pool = values))

        val generateTestsExecution = TestConfig(endpoint = endpoint).generateTestsExecution()

        val urlCodes = values.split(",").map { "$url?$paramName=$it" }

        generateTestsExecution.forEach {
            val generatedUrl = it.toURL()
            println(generatedUrl)
            assertThat(urlCodes).contains(generatedUrl)
        }

    }

    @Test
    fun `should create urls with path param`() {
        val paramName = "code"
        val values = "br,ar,us,col,ger,rus,can,esp,it,par,uru"
        val url = "https://paises.com/search/{code}"

        val endpoint = Endpoint(url = url, method = HttpMethod.GET).addParam(Param.path(paramName, pool = values))

        val generateTestsExecution = TestConfig(endpoint = endpoint).generateTestsExecution()

        val urlCodes = values.split(",").map { url.replace("{$paramName}",it) }

        generateTestsExecution.forEach {
            val generatedUrl = it.toURL()
            println(generatedUrl)
            assertThat(urlCodes).contains(generatedUrl)
        }

    }

}