package br.com.urltester.pool.implementations

class EmailPool : StringPool(1000) {

    override fun getRandomValue(): String {
        val pool = CharPool()

        val name = (1..15).map { pool.getRandomValue() }.joinToString(separator = "")
        val domain = (1..6).map { pool.getRandomValue() }.joinToString(separator = "")
        val type = (1..3).map { pool.getRandomValue() }.joinToString(separator = "")

        return "$name@$domain.$type"
    }

}
