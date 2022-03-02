package com.rail.road.operations.service

import com.entities.Cart
import com.repositories.DestinationRepoImpl
import com.repositories.ReceiverRepoImpl
import jakarta.inject.Singleton


@Singleton
class SortServiceImpl(val destinationRepo: DestinationRepoImpl, val receiverRepo: ReceiverRepoImpl)  {

     fun sort(carts: List<Cart>): List<Cart> {

        val receivers = receiverRepo.scanAll().map { it.name to it.priority }.toMap()
        println(receivers)
         return carts.sortedWith(compareBy({receivers.get(it.destination)}, {it.receiver}))
    }

    fun prueba(unsortedList: List<Cart>): String {

        val receivers = receiverRepo.scanAll().associateBy({it.name}, {it.priority}).toMap()
        println(receivers)
        val x = unsortedList.sortedWith(compareBy({receivers.get(it.destination)}, {it.receiver}))
        println(x)
        return "prueba"
    }

}