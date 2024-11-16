package net.operation.crud.repository;

import net.operation.crud.entity.AddressDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDetailsRepository extends JpaRepository<AddressDetails, Long> {
}