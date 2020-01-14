package com.lxisoft.atm.repository;

import com.lxisoft.atm.domain.Customer;
import com.lxisoft.atm.service.dto.CustomerDTO;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Customer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


	public Customer findByName(@Param("name")String name);
	 
	public Customer findByPin(Integer pin);

}
