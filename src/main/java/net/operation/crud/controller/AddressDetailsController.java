package net.operation.crud.controller;

import net.operation.crud.entity.AddressDetails;
import net.operation.crud.entity.Complaint;
import net.operation.crud.service.AddressDetailsService;
import net.operation.crud.service.ComplaintService;
import net.operation.crud.service.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressDetailsController {

    @Autowired
    private AddressDetailsService addressDetailsService;

    @Autowired
    private ContactDetailsService contactDetailsService;

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("{ticketNo}")
    public ResponseEntity<AddressDetails> addAddressDetails(
            @PathVariable("ticketNo") String ticketNo,
            @RequestBody AddressDetails addressDetails) {

        Complaint complaint = complaintService.getComplaintByTicketNo(ticketNo).orElse(null);

        if (complaint == null) {
            return ResponseEntity.status(404).body(null);
        }
        complaint.setAddressDetails(addressDetails);

        AddressDetails savedAddress = addressDetailsService.addAddressDetails(addressDetails);
        return ResponseEntity.ok(savedAddress);
    }

}
