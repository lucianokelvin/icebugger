package br.com.urltester.pool.implementations

import br.com.urltester.pool.model.PoolModel

class BooleanPool : PoolModel<Boolean>(listOf(true, false)) {

    override fun defaultValues(): List<Boolean> {
        return listOf(true, false)
    }

    override fun convert(value: String?): Boolean {
        return value?.toUpperCase() == "TRUE"
    }


    override fun inc(value: String): Boolean {
        return !convert(value)
    }

    override fun dec(value: String): Boolean {
        return !convert(value)
    }

}