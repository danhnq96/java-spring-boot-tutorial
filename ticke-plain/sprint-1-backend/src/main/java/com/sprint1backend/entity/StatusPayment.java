package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "status_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(40)")
    private String name;

//    -----

    @OneToMany(mappedBy = "statusPayment", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> ticketList;
}
