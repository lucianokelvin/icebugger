package br.com.urltester.exceptions

class InvalidValueException(rule: String) : RuntimeException("Value {$rule} is not valid")