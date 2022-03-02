package com.controllers

import com.model.DestinationEntity
import com.model.ReceiverEntity
import com.models.Destination
import com.services.DestinationServiceImpl
import com.services.ReceiverServiceImpl
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/receiver")
class ReceiverController {


    @Get("/si")
    fun prueba(): String{
        var receivers = service.findAll().map { it.name to it.priority }.toMap()
        println(receivers)

        return "Si"
    }

    @Get("/")
    fun findAll(): List<ReceiverEntity>{
        return service.findAll()
    }

    @Get("/allItems")
    fun scanAll(): MutableIterable<ReceiverEntity>{
        return service.scanAll()
    }

    val service = ReceiverServiceImpl()

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun save(receiver: ReceiverEntity): ReceiverEntity {
        service.save(receiver)
        return receiver
    }

    @Delete("/{name}")
    fun delete(name: String): Boolean {
        return service.deleteByName(name)
    }

    @Patch("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun update(receiver: ReceiverEntity, name: String, priority: Int): ReceiverEntity{
        return service.update(receiver, name, priority)
    }


}