package com.services

import com.model.DestinationEntity
import com.models.Destination
import com.repositories.DestinationRepoImpl
import jakarta.inject.Singleton

@Singleton
class DestinationServiceImpl(){

    val repo = DestinationRepoImpl()

     fun save(destination: DestinationEntity): DestinationEntity {
        return repo.save(destination)
    }

   fun deleteByName(name: String): Boolean {
        if (repo.deleteByName(name) != null) {
            return true
        }
        return false
    }

     fun update(destination: DestinationEntity, name: String, priority: Int): DestinationEntity {
        destination.name = name
        destination.priority = priority
        return repo.update(destination)
    }

    fun findAll(): List<DestinationEntity> {
        return repo.findAll()
    }

}
