package com.sprint1backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "booking_code", columnDefinition = "VARCHAR(40)")
    private String bookingCode;

    @Column(name = "booking_date", columnDefinition = "DATE")
    private LocalDate bookingDate;

//    ------
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppUser appUser;
//    -------

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Ticket> ticketList;
}
