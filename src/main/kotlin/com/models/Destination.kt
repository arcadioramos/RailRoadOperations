package com.models


data class Destination(
     override var type: String? = "destination",
     override var name: String? = null,
     override var priority: Int? = null

) : Catalog(type,name,priority)
