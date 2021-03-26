package br.com.urltester.domain.endpoints

import br.com.urltester.pool.implementations.*
import kotlin.reflect.KClass

enum class ParamType(val poolModel: KClass<*>) {
    CHAR(CharPool::class),
    STRING(StringPool::class),
    NATURAL(NaturalPool::class),
    INTEGER(IntegerPool::class),
    DOUBLE(DoublePool::class),
    FLOAT(FloatPool::class),
    EMAIL(EmailPool::class),
    BOOLEAN(BooleanPool::class),
    DATE(DatePool::class),
    OTHER(GenericPool::class);
}