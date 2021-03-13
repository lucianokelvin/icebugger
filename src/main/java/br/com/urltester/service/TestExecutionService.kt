package br.com.urltester.service

import br.com.urltester.domain.endpoints.TestExecution
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate


@Service
open class TestExecutionService {

    @Transactional
    open fun executeTest(testExecution: TestExecution): TestExecution {
        val restTemplate = RestTemplate()
        val url = testExecution.toURL()
        val headers = HttpEntity("parameters", testExecution.header())

        testExecution.response = try {
            restTemplate
                .exchange(
                    url,
                    testExecution.testConfig.endpoint.method.realMethod,
                    headers,
                    String::class.java
                ).statusCodeValue.toLong()
        } catch (e: HttpClientErrorException) {
            e.statusCode.value().toLong()
        } catch (e: Exception) {
            500L
        }

        return testExecution
    }


}