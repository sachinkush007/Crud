package net.operation.crud.controller;
import net.operation.crud.entity.Complaint;
import net.operation.crud.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {

    @Autowired
    ComplaintService service;

    @PostMapping
    public ResponseEntity<?> registerComplaint(@RequestBody Complaint complaint) {
        if (complaint.getProjectName() == null || complaint.getProjectName().isEmpty()) {
            return new ResponseEntity<>("Project name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (complaint.getModuleName() == null || complaint.getModuleName().isEmpty()) {
            return new ResponseEntity<>("Module name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.registerComplaint(complaint));
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(service.getAllComplaints());
    }

    @GetMapping("/{ticketNo}")
    public ResponseEntity<?> getComplaintById(@PathVariable String ticketNo) {
        Optional<Complaint> complaint= service.getComplaintByTicketNo(ticketNo);
        if (complaint.isPresent()) {
            return new ResponseEntity<>(complaint, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{ticketNo}")
    public ResponseEntity<?> updateComplaint(@PathVariable Long ticketNo, @RequestBody Complaint complaint) {
        if (complaint.getProjectName() == null || complaint.getProjectName().isEmpty()) {
            return new ResponseEntity<>("Project name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (complaint.getModuleName() == null || complaint.getModuleName().isEmpty()) {
            return new ResponseEntity<>("Module name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.updateComplaint(ticketNo, complaint));
    }

    @DeleteMapping("/{ticketNo}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long ticketNo) {
        try {
            service.deleteComplaint(ticketNo);
            return ResponseEntity.ok("Complaint with ticket number " + ticketNo + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Complaint not found with ticket number " + ticketNo);
        }
    }

    @PostMapping("/{ticketNo}/attach")
    public ResponseEntity<String> attachDocument(
            @PathVariable Long ticketNo,
            @RequestParam("file") MultipartFile file) {
        try {
            Complaint updatedComplaint = service.attachDocumentToComplaint(ticketNo, file);
            return ResponseEntity.ok("Document attached to ticket: " + updatedComplaint.getTicketNo());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/{ticketNo}")
//    public Complaint getComplaint(@PathVariable Long ticketNo) {
//        return service.getComplaintByTicketNo(ticketNo);
//    }
}

