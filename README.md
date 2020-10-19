# Icebugger

Tool to check if a particular api rest complies with pre-established rules

## Installation

Download the jar


## Usage

```kotlin
    @Test
    open fun generateRandomTestWithSpecifPool() {
//        Config endpoint
        val endpoint = Endpoint(
                url = "https://restcountries-v1.p.rapidapi.com/alpha/",
                method = HttpMethod.GET
        )

//        Config params
        endpoint.addParam(Param.query("codes", pool = "br, ar, us, col, ger, rus, can, esp, it, par, uru"))
                .addParam(Param.header("X-RapidAPI-Key", pool = "e32bcdbb08msh727433a443862bap1cc32ajsn3bac0c915139"))

//        Config Rules
        val testConfig = TestConfig(endpoint = endpoint)
                .addRule("codes=aaa", 404)

//        Generate and execute Tests
        Assertions.assertTrue(testConfigService.itsAllCorrect(testConfig))
    }
