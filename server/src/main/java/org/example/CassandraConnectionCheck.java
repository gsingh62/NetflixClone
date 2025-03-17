package org.example;

import com.datastax.oss.driver.api.core.CqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CassandraConnectionCheck implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CassandraConnectionCheck.class);

    @Value("${spring.cassandra.contact-points}")
    private String cassandraHost;
    @Value("${spring.cassandra.port}")
    private int cassandraPort;

    private final CqlSession cqlSession;
    public CassandraConnectionCheck(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
    }

    @Override
    public void run(String... args) {
        logger.info("🔵 Making connection to Cassandra at {}:{}.", cassandraHost, cassandraPort);

        if (!cqlSession.getMetadata().getNodes().isEmpty()) {
            logger.info("🟢 Connected to Cassandra at {}:{}.", cassandraHost, cassandraPort);
        } else {
            logger.error("🔴 Failed to connect to Cassandra at {}:{}.", cassandraHost, cassandraPort);
        }
    }
}