package com.sample.xmlread.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Ir21")
public class Ir21 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "DOCUMENT_NAME")
	private String documentName;
	
	@Column(name = "TADIG_CODE")
	private String tadIgCode;
	
	@Column(name = "NETWORK_NAME")
	private String networkName;
}
