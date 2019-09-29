package org.aaron.webflux.coroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class Application {
    @Bean
    fun webClient(): WebClient {
        return WebClient.create()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
