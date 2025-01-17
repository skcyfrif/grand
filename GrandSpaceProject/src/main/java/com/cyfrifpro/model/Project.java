package com.cyfrifpro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal areaInSquareFeet;
    private BigDecimal budget;
    private boolean confirmed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Client client; // A project is assigned to a client

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    @JsonManagedReference
    private Manager manager; // A project is managed by a manager

    private String status; // e.g., IN_PROGRESS, COMPLETED, CONFIRMED, ETC.

    private LocalDate registrationDate; // Field for registration date
    private LocalDate estimatedByVendor; // Field for estimated by vendor date
    
    public void setEstimatedByVendor(LocalDate estimatedByVendor) {
        this.estimatedByVendor = estimatedByVendor;
    }
}
