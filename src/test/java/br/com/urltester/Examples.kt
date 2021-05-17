package br.com.urltester

import br.com.urltester.domain.endpoints.Endpoint
import br.com.urltester.domain.endpoints.HttpMethod
import br.com.urltester.domain.endpoints.TestConfig
import br.com.urltester.domain.endpoints.params.HeaderParam
import br.com.urltester.domain.endpoints.params.PathParam
import br.com.urltester.domain.endpoints.params.QueryParam
import br.com.urltester.service.TestConfigService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
open class Examples {

    @Autowired
    lateinit var testConfigService: TestConfigService

    @Test
    open fun generateRandomTestWithSpecifPool() {
//        Config endpoint
        val endpoint = Endpoint(
            url = "https://restcountries-v1.p.rapidapi.com/alpha/",
            method = HttpMethod.GET
        ).addParam(QueryParam.of("codes", pool = "br, ar, us, col, ger, rus, can, esp, it, par, uru"))
            .addParam(HeaderParam.of("X-RapidAPI-Key", pool = "e32bcdbb08msh727433a443862bap1cc32ajsn3bac0c915139"))

//        Config Rules
        val testConfig = TestConfig(endpoint = endpoint)
            .addRule("codes=aaa", 404)

//        Generate and execute Tests
        Assertions.assertTrue(testConfigService.itsAllCorrect(testConfig))
    }


    @Test
    open fun generateRandomTestNumberAPI() {
        val endpoint = Endpoint(
            url = "https://numbersapi.p.rapidapi.com/{month}/{day}/date?fragment=true&json=true",
            method = HttpMethod.GET
        ).addParam(PathParam.of(name = "month", pool = "1,2,3,4,5,6,7,8,9,10,11,12"))
            .addParam(PathParam.of(name = "day", pool = "1,2,3,4,5,6,7,8,9,10"))
            .addParam(HeaderParam.of(name = "x-rapidapi-host", pool = "numbersapi.p.rapidapi.com"))
            .addParam(HeaderParam.of(name = "useQueryString", pool = "true"))
            .addParam(
                HeaderParam.of(
                    name = "x-rapidapi-key", pool = "e32bcdbb08msh727433a443862bap1cc32ajsn3bac0c915139"
                )
            )

//        Config Rules
        val testConfig = TestConfig(endpoint = endpoint)
            .addRule("month>12", 400L)
            .addRule("month<1", 400L)

//        Generate and execute Tests
        Assertions.assertTrue(testConfigService.itsAllCorrect(testConfig))
    }
}


