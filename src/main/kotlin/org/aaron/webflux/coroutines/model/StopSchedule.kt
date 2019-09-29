package org.aaron.webflux.coroutines.model

import com.fasterxml.jackson.annotation.JsonProperty

data class StopScheduleElement(

        @field:JsonProperty("Actual")
        val actual: Boolean,

        @field:JsonProperty("BlockNumber")
        val blockNumber: Int,

        @field:JsonProperty("DepartureText")
        val departureText: String,

        @field:JsonProperty("DepartureTime")
        val departureTime: String,

        @field:JsonProperty("Description")
        val description: String,

        @field:JsonProperty("Gate")
        val gate: String,

        @field:JsonProperty("Route")
        val route: String,

        @field:JsonProperty("RouteDirection")
        val routeDirection: String,

        @field:JsonProperty("Terminal")
        val terminal: String,
        @field:JsonProperty("VehicleHeading")
        val vehicleHeading: Double,

        @field:JsonProperty("VehicleLatitude")
        val vehicleLatitude: Double,

        @field:JsonProperty("VehicleLongitude")
        val vehicleLongitude: Double


)