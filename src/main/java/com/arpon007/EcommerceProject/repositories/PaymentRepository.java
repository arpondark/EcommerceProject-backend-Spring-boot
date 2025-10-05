package com.arpon007.EcommerceProject.repositories;


import com.arpon007.EcommerceProject.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}