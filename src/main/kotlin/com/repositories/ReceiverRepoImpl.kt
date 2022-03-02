package com.repositories

import com.model.DestinationEntity
import com.model.ReceiverEntity
import com.models.Destination
import jakarta.inject.Singleton
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.net.URI

@Singleton
class ReceiverRepoImpl {

    val table: DynamoDbTable<ReceiverEntity> = dynamoDbTable()

    fun save(destination: ReceiverEntity): ReceiverEntity {
        table.putItem(destination)
        return destination
    }

    fun deleteByName(name: String): ReceiverEntity? {

        val key = Key.builder()
            .partitionValue(AttributeValue.builder().s("receiver").build())
            .sortValue(AttributeValue.builder().s(name).build())
            .build()
        return table.deleteItem { r -> r.key(key) }
    }

    fun update(receiver: ReceiverEntity): ReceiverEntity {
        return table.updateItem(receiver)
    }

    fun findAll() : List<ReceiverEntity>{
        val receivers = ArrayList<ReceiverEntity>()
        val queryConditional = QueryConditional
            .keyEqualTo(
                Key.builder()
                    .partitionValue("receiver")
                    .build()
            )
        val results = table.query(queryConditional).items().iterator();
        while (results.hasNext()) {
            receivers.add(results.next())
        }

        return receivers
    }

    fun scanAll(): MutableIterable<ReceiverEntity>{
        var items = table.scan().items()

        return table.scan().items()

    }


    private fun dynamoDbTable(): DynamoDbTable<ReceiverEntity> {

        val region = Region.of("us-east-1")

        val dynamoDbClient = DynamoDbClient.builder()
            .endpointOverride(URI.create("http://localhost:8000"))
            .region(region)
            .build()
        val dynamoDbClientEnhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build()

        return dynamoDbClientEnhancedClient
            .table("TestTable", TableSchema.fromBean(ReceiverEntity::class.java))
    }

}