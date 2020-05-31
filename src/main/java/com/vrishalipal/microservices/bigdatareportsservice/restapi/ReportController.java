package com.vrishalipal.microservices.bigdatareportsservice.restapi;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrishalipal.microservices.bigdatareportsservice.model.MobileTransaction;
import com.vrishalipal.microservices.bigdatareportsservice.repositories.MobileTransactionRepository;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	
	@Autowired
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private MobileTransactionRepository repository;
	
	@GetMapping("/fraud/rawdata")
	public List<MobileTransaction> getFraudData() {
		
//		Pageable topTwenty = PageRequest.of(0, 20);
//		return repository.findByIsFraud(topTwenty);
		return repository.findByIsFraud();
					
	}
	
}
