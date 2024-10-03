package com.mailing.shippingandhandling.fedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mailing.shippingandhandling.fedex.entity.Fedex;

public interface FedexRepository extends JpaRepository<Fedex, Long> {

}
