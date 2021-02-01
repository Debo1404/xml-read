package com.sample.xmlread.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Data
@Table(name = "IPsubnet")
public class IPsubnet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ir21Id",referencedColumnName="id")
	private Ir21 ir21Id;
	
	private String rawIp;
	
	private String subnetMask;

	private String ipV4Address;
	
	@CreatedDate
	private Timestamp createdDate;
}
