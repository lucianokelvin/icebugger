package br.com.urltester.pool.implementations

import br.com.urltester.pool.model.PoolModel

class BooleanPool : PoolModel<Boolean>(listOf(true, false)) {

    override fun convert(value: String?): Boolean {
        return value == "true"
    }

    override fun inc(value: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun dec(value: String): Boolean {
        TODO("Not yet implemented")
    }

}