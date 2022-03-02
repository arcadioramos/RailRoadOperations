package com.services
import com.model.DestinationEntity
import com.model.ReceiverEntity
import com.repositories.ReceiverRepoImpl
import jakarta.inject.Singleton
import javax.sound.midi.Receiver

@Singleton
class ReceiverServiceImpl(){

    val repo = ReceiverRepoImpl()

    fun save(receiver: ReceiverEntity): ReceiverEntity {
        return repo.save(receiver)
    }

    fun deleteByName(name: String): Boolean {
        if (repo.deleteByName(name) != null) {
            return true
        }
        return false
    }

    fun findAll(): List<ReceiverEntity>{
        return repo.findAll()
    }

    fun scanAll(): MutableIterable<ReceiverEntity>{
        return repo.scanAll()
    }

    fun update(receiver: ReceiverEntity, name: String, priority: Int): ReceiverEntity {
        receiver.name = name
        receiver.priority = priority
        return repo.update(receiver)
    }

}
