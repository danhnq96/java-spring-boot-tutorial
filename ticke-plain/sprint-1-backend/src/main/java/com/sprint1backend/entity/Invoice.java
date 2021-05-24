package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "invoice_code", columnDefinition = "VARCHAR(40)")
    private String invoiceCode;

    @Column(name = "form_code", columnDefinition = "VARCHAR(40)")
    private String formCode;

    @Column(name = "date_created", columnDefinition = "DATETIME")
    private LocalDateTime dateCreated;

    @Column(name = "total_money", columnDefinition = "DOUBLE")
    private Double totalMoney;

//    ------
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppUser appUser;

//-----
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> ticketList;
}
