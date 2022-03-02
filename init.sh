#!/bin/bash

export AWS_PAGER=""

# Drop
aws dynamodb delete-table --endpoint http://localhost:8000 --table-name TestTable


# Create
aws dynamodb create-table --endpoint http://localhost:8000 --table-name TestTable \
  --attribute-definitions AttributeName=type,AttributeType=S AttributeName=name,AttributeType=S \
    --key-schema AttributeName=type,KeyType=HASH AttributeName=name,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

# Insert
aws dynamodb put-item --endpoint http://localhost:8000  --table-name TestTable \
    --item '{"type":{"S":"destination"}, "name": {"S": "Chicago"}, "priority": {"N": "2"}}'

aws dynamodb put-item --endpoint http://localhost:8000  --table-name TestTable \
--item '{"type":{"S":"destination"}, "name": {"S": "Houston"}, "priority": {"N": "1"}}'

aws dynamodb put-item --endpoint http://localhost:8000  --table-name TestTable \
--item '{"type":{"S":"destination"}, "name": {"S": "LA"}, "priority": {"N": "3"}}'

aws dynamodb put-item --endpoint http://localhost:8000  --table-name TestTable \
--item '{"type":{"S":"receiver"}, "name": {"S": "FedEx"}, "priority": {"N": "2"}}'

aws dynamodb put-item --endpoint http://localhost:8000  --table-name TestTable \
--item '{"type":{"S":"receiver"}, "name": {"S": "UPS"}, "priority": {"N": "1"}}'

aws dynamodb put-item --endpoint http://localhost:8000  --table-name TestTable \
--item '{"type":{"S":"receiver"}, "name": {"S": "Old Dominion"}, "priority": {"N": "3"}}'

# Disply all values in table
aws dynamodb scan --endpoint http://localhost:8000  --table-name TestTables