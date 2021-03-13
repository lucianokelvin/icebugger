package br.com.urltester.pool.implementations

class EmailPool : StringPool(1000) {

    override fun getRandomValue(): String {
        val poll = CharPool()

        val name = (1..15).map { poll.getRandomValue() }.joinToString(separator = "")
        val domain = (1..6).map { poll.getRandomValue() }.joinToString(separator = "")
        val type = (1..3).map { poll.getRandomValue() }.joinToString(separator = "")

        return "$name@$domain.$type"
    }

}
