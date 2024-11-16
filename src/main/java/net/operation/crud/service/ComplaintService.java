package net.operation.crud.service;
import net.operation.crud.entity.Complaint;
import net.operation.crud.repository.AddressDetailsRepository;
import net.operation.crud.repository.ComplaintRepository;
import net.operation.crud.repository.ContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComplaintService {

    @Autowired
     ComplaintRepository repository;

    @Autowired
    private AddressDetailsRepository addressDetailsRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    public Complaint registerComplaint(Complaint complaint) {
        complaint.setTicketNo(UUID.randomUUID().toString());
        complaint.setCreateDate(LocalDateTime.now());
        return repository.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    public Optional<Complaint> getComplaintByTicketNo(String ticketNo) {
        return repository.findByTicketNo(ticketNo);
    }

        public Complaint updateComplaint(Long ticketNo, Complaint updatedComplaint) {
            Complaint complaint = repository.findById(ticketNo)
                    .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + ticketNo));

            complaint.setProjectName(updatedComplaint.getProjectName());
            complaint.setModuleName(updatedComplaint.getModuleName());
            complaint.setSubModuleName(updatedComplaint.getSubModuleName());
            complaint.setFrequency(updatedComplaint.getFrequency());
            complaint.setPriority(updatedComplaint.getPriority());
            complaint.setIssueDescription(updatedComplaint.getIssueDescription());
            complaint.setStatus(updatedComplaint.getStatus());

            return repository.save(complaint);
        }

    public void deleteComplaint(Long ticketNo) {
        Complaint complaint = repository.findById(ticketNo)
                .orElseThrow(() -> new RuntimeException("Complaint not found" + ticketNo));
        repository.delete(complaint);
    }

    public Complaint attachDocumentToComplaint(Long ticketNo, MultipartFile file) throws Exception {
        Complaint complaint = repository.findById(ticketNo)
                .orElseThrow(() -> new RuntimeException("user not found"));
        if (file != null && !file.isEmpty()) {
            complaint.setAttachment(file.getBytes());
        } else {
            throw new RuntimeException("File is empty or missing");
        }
        return repository.save(complaint);
    }

//    public Complaint getComplaintByTicketNo(Long ticketNo) {
//        return repository.findByTicketNo(ticketNo);
//    }

    public Complaint addComplaint(Complaint complaint) {
        return repository.save(complaint);
    }
}

