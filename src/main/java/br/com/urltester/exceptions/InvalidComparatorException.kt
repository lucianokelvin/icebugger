package br.com.urltester.exceptions

import br.com.urltester.domain.rules.Comparator

class InvalidComparatorException(comparator: Comparator) :
    RuntimeException("Comparator {$comparator} is not valid for this Pool")