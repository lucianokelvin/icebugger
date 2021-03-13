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

        var corrects = 0L
        var erros = 0L

        generateTestsExecution.forEach {
            testExecutionService.executeTest(it)

            if (it.isCorrect()) {
                corrects++
            } else {
                erros++
            }
        }

        val executionsResuts = generateTestsExecution.groupBy { it.isCorrect() }
        val errorsTests = executionsResuts[false] ?: emptyList()
        val correctsTests = executionsResuts[true] ?: emptyList()

        println("Total : ${generateTestsExecution.size}")
        println("Corrects Tests : ${correctsTests.size}")
        println("Failed Tests : ${errorsTests.size}")

        if (erros > 0L) {
            println("##################### FAILED URlS ###############################")
            errorsTests.forEach {
                it.printAsFailed()
            }
            println("#################################################################")
        }

        return errorsTests.isEmpty()

    }


}