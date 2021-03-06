package org.aaron.webflux.coroutines.service

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import mu.KotlinLogging
import org.aaron.webflux.coroutines.model.StopScheduleElement
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

private val logger = KotlinLogging.logger {}

@Service
class StopService(private val webClient: WebClient) {

    suspend fun getStopSchedule(id: String): List<StopScheduleElement>? =
            try {
                val url = "https://svc.metrotransit.org/NexTrip/${id}"
                logger.info { "making call to $url" }

                val exchange = webClient
                        .get()
                        .uri(url)
                        .accept(MediaType.APPLICATION_JSON)
                        .awaitExchange()

                logger.info { "exchange.rawStatusCode = ${exchange.rawStatusCode()}" }

                if (!exchange.statusCode().is2xxSuccessful) {
                    null
                } else {
                    val stopScheduleList = exchange
                            .awaitBody<List<StopScheduleElement>>()

                    logger.info { "got stopScheduleList size ${stopScheduleList.size}" }

                    stopScheduleList
                }
            } catch (e: Exception) {
                logger.warn(e) { "getStopSchedule caught exception for '${id}'" }
                null
            }


    suspend fun getStopSchedules(ids: List<String>): Map<String, List<StopScheduleElement>?> =
            coroutineScope {
                logger.info { "in getStops ids.size = ${ids.size} ids = $ids" }

                val deferredStopSchedules = ids.map { id ->
                    id to async {
                        getStopSchedule(id)
                    }
                }

                deferredStopSchedules.map { idAndResult ->
                    idAndResult.first to idAndResult.second.await()
                }.toMap()
            }

}