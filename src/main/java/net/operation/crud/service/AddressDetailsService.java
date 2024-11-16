package net.operation.crud.service;

import net.operation.crud.entity.AddressDetails;
import net.operation.crud.repository.AddressDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressDetailsService {

    @Autowired
    private AddressDetailsRepository addressDetailsRepository;

    public AddressDetails addAddressDetails(AddressDetails addressDetails) {
        return addressDetailsRepository.save(addressDetails);
    }
}

