package br.com.urltester.pool.implementations

import br.com.urltester.domain.rules.Comparator
import br.com.urltester.pool.model.PoolModel
import java.time.LocalDate
import java.util.concurrent.ThreadLocalRandom

class DatePool(private val from: LocalDate = LocalDate.MIN, private val to: LocalDate = LocalDate.MAX) :
    PoolModel<LocalDate>() {

    override fun getRandomValue(): LocalDate {
        val startEpochDay = from.toEpochDay()
        val endEpochDay = to.toEpochDay()
        val randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay)
        return LocalDate.ofEpochDay(randomDay)
    }

    override fun convert(value: String?): LocalDate? {
        value?.let {
            val year = it.substring(0, 4).toInt()
            val month = it.substring(5, 7).toInt()
            val day = it.substring(8, 10).toInt()

            return LocalDate.of(year, month, day)
        }

        return null
    }


    override fun compare(val1: String, val2: String, comparator: Comparator): Boolean {
        return when (comparator) {
            Comparator.EQUALS -> convert(val1) == convert(val2)
            Comparator.DIFFERENT -> convert(val1) != convert(val2)
            Comparator.GREATER_THAN -> convert(val1)!! > convert(val2)
            Comparator.LESS_THAN -> convert(val1)!! < convert(val2)
            Comparator.GREATER_THAN_EQUALS -> convert(val1)!! >= convert(val2)
            Comparator.LESS_THAN_EQUALS -> convert(val1)!! <= convert(val2)
            else -> false
        }
    }

    override fun inc(val1: String): String {
        val converted = convert(val1)
        return converted?.plusDays(1).toString()
    }

    override fun dec(val1: String): String {
        val converted = convert(val1)
        return converted?.minusDays(1).toString()
    }

}