package com.vrishalipal.microservices.bigdatareportsservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MobileTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer step;
	
	@Column(length = 10)
	private String type;
	
	private BigDecimal amount;
	
	@Column(length = 32)
	private String nameOrig;
	
	private BigDecimal oldBalanceOrg;
	
	private BigDecimal newBalanceOrig;
	
	@Column(length = 32)
	private String nameDest;
	
	private BigDecimal oldBalanceDest;
	
	private BigDecimal newBalanceDest;
	
	private Integer isFraud;
	
	private Integer isFlaggedFraud;
}
