package net.operation.crud.repository;

import net.operation.crud.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
}
