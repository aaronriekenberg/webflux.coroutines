package org.aaron.webflux.coroutines.controller

import mu.KotlinLogging
import org.aaron.webflux.coroutines.model.StopScheduleElement
import org.aaron.webflux.coroutines.service.StopService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
class CoroutinesRestController(
        private val stopService: StopService) {

    @GetMapping("/stop/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getStop(@PathVariable id: String): ResponseEntity<List<StopScheduleElement>> {
        logger.info { "in getStop id = $id" }

        val stopScheduleElement = stopService.getStopSchedule(id)
        return if (stopScheduleElement == null) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(stopScheduleElement)
        }
    }

    @GetMapping("/stops", produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun getStops(@RequestParam ids: List<String>): Map<String, List<StopScheduleElement>?> {
        logger.info { "in getStops ids = $ids" }

        return stopService.getStopSchedules(ids)
    }

}