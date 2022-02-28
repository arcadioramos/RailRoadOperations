package com.repositories

import com.model.DestinationEntity
import com.models.Destination
import jakarta.inject.Singleton
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.net.URI

@Singleton
class DestinationRepoImpl {

    val table: DynamoDbTable<DestinationEntity> = dynamoDbTable()

    fun save(destination: DestinationEntity): DestinationEntity {
        table.putItem(destination)
        return destination
    }

    fun deleteByName(name: String): DestinationEntity? {

        val key = Key.builder()
            .partitionValue(AttributeValue.builder().s("destination").build())
            .sortValue(AttributeValue.builder().s(name).build())
            .build()
        return table.deleteItem { r -> r.key(key) }
    }


    private fun dynamoDbTable(): DynamoDbTable<DestinationEntity> {

        val region = Region.of("us-east-1")

        val dynamoDbClient = DynamoDbClient.builder()
            .endpointOverride(URI.create("http://localhost:8000"))
            .region(region)
            .build()
        val dynamoDbClientEnhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build()

        return dynamoDbClientEnhancedClient
            .table("TestTable", TableSchema.fromBean(DestinationEntity::class.java))
    }

}