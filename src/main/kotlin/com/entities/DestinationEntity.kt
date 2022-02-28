package com.model

import com.models.Catalog
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey

@DynamoDbBean
data class DestinationEntity(
    @get:DynamoDbPartitionKey
    override var type: String? = "destination",
    @get: DynamoDbSortKey
    override var name: String? = null,
    override var priority: Int? = null


) : Catalog()
