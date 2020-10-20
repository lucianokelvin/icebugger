package br.com.urltester.domain.rules

enum class Comparator(var symbol: String) {
    EQUALS("="),
    DIFFERENT("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_EQUALS(">="),
    LESS_THAN_EQUALS("<="),
    STARTS_WITH("%*"),
    ENDS_WITH("*%"),
    CONTAINS("%*%"),
    NOT_CONTAINS("!%*%");
}