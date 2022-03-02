package com.models

data class Receiver(
    override var type: String? = "receiver",
    override var name: String? = null,
    override var priority: Int? = null

) : Catalog(type,name,priority)