package net.operation.crud.controller;

import net.operation.crud.entity.Complaint;
import net.operation.crud.entity.ContactDetails;
import net.operation.crud.service.ComplaintService;
import net.operation.crud.service.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactDetailsController {

    @Autowired
    private ContactDetailsService contactDetailsService;

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/{ticketNo}/contact")
    public ResponseEntity<ContactDetails> addContactDetails(
            @PathVariable("ticketNo") String ticketNo,
            @RequestBody ContactDetails contactDetails) {

        Complaint complaint = complaintService.getComplaintByTicketNo(ticketNo).orElse(null);

        if (complaint == null) {
            return ResponseEntity.status(404).body(null);
        }

        complaint.setContactDetails(contactDetails);
        contactDetails.setComplaint(complaint);

        ContactDetails savedContact = contactDetailsService.addContactDetails(contactDetails);
        return ResponseEntity.ok(savedContact);
    }
}
