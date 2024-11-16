package net.operation.crud.repository;

import net.operation.crud.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    public Optional<Complaint> findByTicketNo(String ticketNo);
}
