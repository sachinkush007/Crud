package net.operation.crud.service;

import net.operation.crud.entity.ContactDetails;
import net.operation.crud.repository.ContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactDetailsService {

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    public ContactDetails addContactDetails(ContactDetails contactDetails) {
        return contactDetailsRepository.save(contactDetails);
    }
}
