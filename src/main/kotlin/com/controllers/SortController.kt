package com.rail.road.operations.controller

import com.entities.Cart
import com.rail.road.operations.service.SortServiceImpl
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/sort")
class SortController(val sortService: SortServiceImpl) {

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun sort(@Body carts: List<Cart>): List<Cart> {
        return sortService.sort(carts)
    }

    @Post("/prueba")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun prueba(unsortedList: List<Cart>): String {
        return sortService.prueba(unsortedList)
    }

}