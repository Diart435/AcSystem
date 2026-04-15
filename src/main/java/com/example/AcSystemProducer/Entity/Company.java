package com.example.AcSystemProducer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToOne
    private User ceo;

    public Company(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
}
