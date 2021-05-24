package com.sprint3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "content", columnDefinition = "VARCHAR(250)")
    private String content;

    @Column(name = "noti_url", columnDefinition = "TEXT")
    private String notiURL;

    @Column(name = "status", columnDefinition = "BIT")
    private Boolean status = false;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    private LocalDateTime createDate;

    // relationship

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_account_id", referencedColumnName = "id", columnDefinition = "BIGINT")
    private AppAccount appAccount;
}
