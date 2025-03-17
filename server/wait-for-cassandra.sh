#!/bin/bash

CASSANDRA_HOST=cassandra
CASSANDRA_PORT=9042
MAX_RETRIES=10
RETRY_INTERVAL=10

echo "Waiting for Cassandra at ${CASSANDRA_HOST}:${CASSANDRA_PORT} to be ready..."

success=false
for ((i=1;i<=MAX_RETRIES;i++)); do
    if nc -z $CASSANDRA_HOST $CASSANDRA_PORT; then
        echo "Cassandra is ready."
        success=true
        break
    else
        echo "Cassandra is not ready yet. Attempt ${i}/${MAX_RETRIES}."
        sleep $RETRY_INTERVAL
    fi
done

if [ "$success" = true ] ; then
    echo "Create keyspace..."
    cqlsh -e "CREATE KEYSPACE IF NOT EXISTS mykeyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};" cassandra 9042
    cqlsh -e "CREATE TABLE customer ( customerId INT PRIMARY KEY, firstName TEXT, lastName TEXT, email TEXT );" cassandra 9042

    echo "Done!"

    echo "Start Spring Boot application."
    exec java -jar app.jar
else
    echo "Failed to connect to Cassandra after ${MAX_RETRIES} attempts."
fi