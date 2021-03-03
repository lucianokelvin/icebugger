package br.com.urltester.pool.implementations

import br.com.urltester.exceptions.InvalidValueException
import br.com.urltester.pool.model.PoolModel

class CharPool : PoolModel<Char>(('a'..'z').toList()) {

    override fun convert(value: String?): Char {
        return value?.get(0) ?: throw InvalidValueException(value ?: "")
    }

}