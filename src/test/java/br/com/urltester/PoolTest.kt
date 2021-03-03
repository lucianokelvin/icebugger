package br.com.urltester

import br.com.urltester.domain.endpoints.Param
import br.com.urltester.domain.rules.Rule
import br.com.urltester.pool.implementations.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PoolTest {


    @Test
    fun generateRandomPool() {
        val email = EmailPool().new().getRandomValue()
        println(email)
        val string = StringPool(50).new().getRandomValue()
        println(string)
        val natural = NaturalPool(10).new().getRandomValue()
        println(natural)
        val integer = IntegerPool(min = -100, max = 100).new().getRandomValue()
        println(integer)

        val generic = GenericPool("banana, maça, uva, pera, melancia").new().getRandomValue()
        println(generic)

        assert(email.isNotEmpty())

        assert(string.isNotEmpty())
        assert(string.length <= 50)

        assert(natural in 0..10)

        assert(integer in -100..100)

        assert("banana, maça, uva, pera, melancia".contains(generic))
    }


    @Test
    fun generateRandomPoolList() {
        val emailList = EmailPool().new().randomList(10L)
        val emailListNullable = EmailPool().new().randomList(shouldHaveNullValue = true, quantity = 10L)
        val emailListSize = EmailPool().new().randomList(quantity = 20L)
        val emailListSizeNullable = EmailPool().new().randomList(quantity = 20L, shouldHaveNullValue = true)

        assert(emailList.size == 10 && !emailList.contains(null))
        assert(emailListNullable.size == 10 && emailListNullable.contains(null))
        assert(emailListSize.size == 20 && !emailListSize.contains(null))
        assert(emailListSizeNullable.size == 20 && emailListSizeNullable.contains(null))
    }


    @Test
    fun generateRandomPoolListWithRules() {
        val emails = listOf("teste@teste.com", "admin@teste.com")

        val emailPool = EmailPool().new()

        val param = Param(name = "email", type = "String")

        val rule1 = Rule(param = param, value = emails[0])
        val rule2 = Rule(param = param, value = emails[1])

        val list = emailPool.randomList(rules = listOf(rule1, rule2), quantity = 10L)
        val listWithNull = emailPool.randomList(rules = listOf(rule1, rule2), shouldHaveNullValue = true, quantity = 15)

        assert(list.size == 10 && list.containsAll(emails))
        assert(listWithNull.size == 15 && listWithNull.containsAll(emails) && listWithNull.contains(null))
    }
}