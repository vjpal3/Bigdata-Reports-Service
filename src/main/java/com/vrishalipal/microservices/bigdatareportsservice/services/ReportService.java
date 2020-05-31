package com.vrishalipal.microservices.bigdatareportsservice.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.vrishalipal.microservices.bigdatareportsservice.model.MobileTransaction;
import com.vrishalipal.microservices.bigdatareportsservice.repositories.MobileTransactionRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	
	@Autowired 
	private MobileTransactionRepository repository;
	
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		List<MobileTransaction> fraudTrasanctions = repository.findByIsFraud();
		
		//Load file and compile it
		File file = ResourceUtils.getFile("classpath:fraud_transactions.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fraudTrasanctions);
		
		Map<String, Object> parameters = new HashMap<>();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		String path = "E:\\Vrishali\\careerdevs-2019\\java-repos-2020\\CapstoneBigDataMSA\\reports";
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,  path + "\\fraud_transactions.html");
		}
		
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\fraud_transactions.pdf");
		}
		
		return "Report generated in path: " + path;
	}
}
