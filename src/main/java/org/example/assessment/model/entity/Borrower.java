package org.example.assessment.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_borrower")
@Data
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_address", length = 255)
    private String emailAddress;
}
