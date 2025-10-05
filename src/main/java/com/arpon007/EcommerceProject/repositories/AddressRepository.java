package com.arpon007.EcommerceProject.repositories;

import com.arpon007.EcommerceProject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AddressRepository extends JpaRepository<Address, Long> {
}
