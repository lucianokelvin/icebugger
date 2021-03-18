package br.com.urltester.service

import br.com.urltester.domain.endpoints.TestConfig
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Slf4j
open class TestConfigService {

    @Autowired
    lateinit var testExecutionService: TestExecutionService

    @Transactional
    open fun itsAllCorrect(testConfig: TestConfig): Boolean {
        val generateTestsExecution = testConfig.generateTestsExecution()


        generateTestsExecution.forEach {
            testExecutionService.executeTest(it)
        }

        val executionResults = generateTestsExecution.groupBy { it.isCorrect() }
        val correctsTests = executionResults[true] ?: emptyList()
        val errorsTests = executionResults[false] ?: emptyList()

        println("Total : ${generateTestsExecution.size}")
        println("Corrects Tests : ${correctsTests.size}")
        println("Failed Tests : ${errorsTests.size}")

        if (errorsTests.size > 0L) {
            println("##################### FAILED URlS ###############################")
            errorsTests.forEach {
                it.printAsFailed()
            }
            println("#################################################################")
        }

        return errorsTests.isEmpty()

    }


}