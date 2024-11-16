package net.operation.crud.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticketNo;

    private LocalDateTime createDate;
    @Column(nullable = false)
    private String projectName;
    @Column(nullable = false)
    private String moduleName;
    private String subModuleName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.NEW;

    @Lob
    private byte[] attachment;


    @OneToOne(cascade = CascadeType.ALL)
    private AddressDetails addressDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactDetails contactDetails;
}

enum Frequency {
    ALWAYS,
    RANDOM
}

enum Priority {
    CRITICAL,
    HIGH,
    MEDIUM
}

enum Status {
    NEW,
    ASSIGNED,
    RESOLVED,
    CLOSED
}
