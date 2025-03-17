package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Table
public class Customer {
    @PrimaryKey
    private @NonNull UUID customerId;
    private @NonNull String firstName;
    private @NonNull String lastName;
    private @NonNull String email;
}
