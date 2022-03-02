package com.entities

import com.fasterxml.jackson.annotation.JsonIgnore

data class Cart(
    val name: String? = null, val destination: String?=null,
    val receiver: String? = null,
    var destinationPriority: Int? = null,
    @get:JsonIgnore
    var receiverPriority: Int? = null
)