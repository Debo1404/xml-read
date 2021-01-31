package com.sample.xmlread.model;

import java.sql.Timestamp;

import javax.persistence.Column;
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
	
	@ManyToOne
	@JoinColumn(name = "ID")
	private Ir21 ir21Id;
	
	@Column(name = "RAW_IP")
	private String rawIp;
	
	@Column(name = "SUBNET_MASK")
	private String subnetMask;
	
	@Column(name = "ipV4_ADDRESS")
	private String ipV4Address;
	
	@Column(name="CREATED_DATE")
	@CreatedDate
	private Timestamp createdDate;
}
