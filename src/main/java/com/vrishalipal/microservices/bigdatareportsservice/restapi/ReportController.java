package com.vrishalipal.microservices.bigdatareportsservice.restapi;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vrishalipal.microservices.bigdatareportsservice.model.MobileTransaction;
import com.vrishalipal.microservices.bigdatareportsservice.repositories.MobileTransactionRepository;
import com.vrishalipal.microservices.bigdatareportsservice.services.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	
	@Autowired
	private MobileTransactionRepository repository;
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/fraud/rawdata")
	public List<MobileTransaction> getFraudData() {
		
//		Pageable topTwenty = PageRequest.of(0, 20);
//		return repository.findByIsFraud(topTwenty);
		return repository.findByIsFraud();
					
	}
	
	@GetMapping("/fraud/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return service.exportReport(format);
	}
	
	@GetMapping("/fraud")
	public String generatePDFHtmlReports() throws FileNotFoundException, JRException {
		return service.exportReport();
	}
	
}
