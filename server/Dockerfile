FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/NetflixClone-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080


RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    python3-pip \
    && rm -rf /var/lib/apt/lists/*

RUN pip3 install cqlsh

RUN apt-get update && apt-get install -y netcat

COPY wait-for-cassandra.sh .

RUN chmod +x wait-for-cassandra.sh

ENTRYPOINT ["./wait-for-cassandra.sh"]
