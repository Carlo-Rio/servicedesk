package com.example.ServiceDeskV1.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.Instant;

@Entity
@Table(name = "tickets", schema = "servicedesk")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length =  150)
    private String title;

    @Column(name = "description", nullable = false, length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length =  20)
    private Status status = Status.NEW;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 20)
    private Priority priority = Priority.P3;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    private AppUser customer;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name ="assignee_id")
    private AppUser assignee;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Ticket() {

    }

    public Ticket(String title, String description, AppUser customer) {
        this.title = title;
        this.description = description;
        this.customer = customer;
    }

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }
    @PreUpdate
    protected  void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
