package br.com.urltester.utils

import java.lang.Double.parseDouble

class Utils {

    companion object {

        fun isNumeric(value: String): Boolean {
            try {
                parseDouble(value)
            } catch (e: NumberFormatException) {
                return false
            }

            return true
        }

    }
}