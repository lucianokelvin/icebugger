package br.com.urltester.pool.implementations

import br.com.urltester.exceptions.InvalidValueException
import br.com.urltester.pool.model.PoolModel

class CharPool : PoolModel<Char>(('a'..'z').toList()) {

    override fun convert(value: String?): Char {
        return value?.get(0) ?: throw InvalidValueException(value ?: "")
    }

    override fun inc(value: String): Char {
        return convert(value).inc()
    }

    override fun dec(value: String): Char {
        return convert(value).dec()
    }


}