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
	
	private String path = "E:\\Vrishali\\careerdevs-2019\\java-repos-2020\\CapstoneBigDataMSA\\reports";
	
	private JasperPrint jasperPrint;
	
	public String exportReport() throws FileNotFoundException, JRException {
		configureJasperReport();
		exportPDFReport();
		exportHTMLReport();
		return "Report generated in path: " + path;
	}
	
	public void configureJasperReport() throws FileNotFoundException, JRException {
		
		List<MobileTransaction> fraudTrasanctions = repository.findByIsFraud();
		
		//Load file and compile it
		File file = ResourceUtils.getFile("classpath:fraud_transactions.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fraudTrasanctions);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Vrishali Pal");
		
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	}
	
	public void exportPDFReport() throws JRException {
		JasperExportManager.exportReportToPdfFile(jasperPrint,  path + "\\fraud_transactions.pdf");
	}
	
	public void exportHTMLReport() throws JRException {
		JasperExportManager.exportReportToHtmlFile(jasperPrint,  path + "\\fraud_transactions.html");
	}
	
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException  {
		configureJasperReport();
		
		if(reportFormat.equalsIgnoreCase("html")) {
			exportHTMLReport();
		}
		
		if(reportFormat.equalsIgnoreCase("pdf")) {
			exportPDFReport();
		}
		return "Report generated in path: " + path;
	}
}
