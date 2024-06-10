package org.example.google;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Setter
@Getter
@Data
@Entity
@Table(name = "UserR")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private UserRole role;
}