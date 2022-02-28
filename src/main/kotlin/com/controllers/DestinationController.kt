package com.controllers

import com.model.DestinationEntity
import com.models.Destination
import com.services.DestinationServiceImpl
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces

@Controller("/destinations")
class DestinationController {




    @Get("/")
    fun helloWorld(): String{
        return "Hello world"
    }

    val service = DestinationServiceImpl()

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun save(destination: DestinationEntity): DestinationEntity {
        service.save(destination)
        return destination
    }

    @Delete("/{name}")
    fun delete(name: String): Boolean {
        return service.deleteByName(name)
    }


}