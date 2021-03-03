package br.com.urltester.exceptions

class InvalidRuleException(rule: String) : RuntimeException("Rule {$rule} is not valid")