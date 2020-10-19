package br.com.urltester.service

import br.com.urltester.domain.endpoints.TestConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
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
        val errorsTests = generateTestsExecution.filter { !it.isCorrect() }

        if (erros > 0L) {
            println("Total : ${generateTestsExecution.size}")
            println("Corrects Tests : ${generateTestsExecution.filter { it.isCorrect() }.size}")
            println("Failed Tests : ${errorsTests.size}")

            println("##################### FAILED URlS ###############################")
            errorsTests.forEach {
                it.printAsFailed()
            }
            println("#################################################################")
        }

        return errorsTests.isEmpty()

    }


}