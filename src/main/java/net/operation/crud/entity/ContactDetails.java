package net.operation.crud.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "(\\d{10}|[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})", message = "Invalid Contact")
    private String contact;

    private String contactType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ticketNo")
    private Complaint complaint;
}

enum AddStatus {
    ACTIVE,
    INACTIVE
}