

package com.brodygaudel.ebank.entities;

import com.brodygaudel.ebank.enums.Sex;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String placeOfBirth;
    private String nationality;

    @Column(unique = true, nullable = false)
    private String cin;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(mappedBy = "customer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BankAccount> bankAccounts;
}
