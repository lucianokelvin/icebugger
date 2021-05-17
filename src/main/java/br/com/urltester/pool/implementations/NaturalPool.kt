package br.com.urltester.pool.implementations

class NaturalPool(val max: Int = Integer.MAX_VALUE) : IntegerPool(min = 0, max = max) {
    override fun defaultValues(): List<Int> {
        val values = mutableListOf<Int>()
        values.add(0)

        if (max > 0) {
            values.add((0..max).random())
        }

        return values
    }

}