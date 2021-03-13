package br.com.urltester

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GenerateJunitClass {

    @Test
    @Disabled
    fun generateHelloWorld() {
        val greeterClass = ClassName("", "Greeter")
        val file = FileSpec.builder("", "HelloWorld").addType(
            TypeSpec.classBuilder("IcuBuggerTest")
                .addFunction(
                    FunSpec.builder("greet")
                        .addStatement("println(%P)", "Hello, \$name")
                        .addAnnotation(Test::class)
                        .build()
                )
                .build()
        ).build()

        file.writeTo(System.out)


    }

}