package net.operation.crud.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN Number")
    private String panNumber;

    @Pattern(regexp = "\\d{12}", message = "Invalid Aadhaar Number")
    private String aadhaarNumber;

    private String city;

    @Pattern(regexp = "\\d{6}", message = "Invalid Pin Code")
    private String pinCode;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "ticketNo")
    private Complaint complaint;
}