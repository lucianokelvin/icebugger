package br.com.urltester.domain.endpoints

import br.com.urltester.pool.implementations.*
import br.com.urltester.pool.model.PoolModel

enum class ParamType(val poolModel: PoolModel<*>) {
    CHAR(CharPool()),
    STRING(StringPool()),
    NATURAL(NaturalPool()),
    INTEGER(IntegerPool()),
    DOUBLE(DoublePool()),
    FLOAT(FloatPool()),
    EMAIL(EmailPool()),
    BOOLEAN(BooleanPool()),
    DATE(DatePool()),
    OTHER(GenericPool(""));

    fun pool(values: String?): PoolModel<*> {
        if (this == OTHER && values != null) {
            return GenericPool(values)
        }

        return this.poolModel
    }


}