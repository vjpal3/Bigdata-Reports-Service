package com.vrishalipal.microservices.bigdatareportsservice.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vrishalipal.microservices.bigdatareportsservice.model.MobileTransaction;

public interface MobileTransactionRepository extends JpaRepository<MobileTransaction, Long>{
	
	@Query("Select a from MobileTransaction a where a.isFraud = 1")
//	public List<MobileTransaction> findByIsFraud(Pageable pageable); 
	
	public List<MobileTransaction> findByIsFraud(); 
	
}
