package br.com.urltester.domain.endpoints

import org.springframework.http.HttpMethod as RealMethod

enum class HttpMethod(val realMethod: RealMethod) {
    GET(RealMethod.GET),
    POST(RealMethod.POST),
    PUT(RealMethod.PUT),
    DELETE(RealMethod.DELETE);
}