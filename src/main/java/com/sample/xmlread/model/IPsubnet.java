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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel (
	    value       = "IPSubnet values",
	    description = "This table hold value related to IP and Subnet."
	)
@Entity
@Data
@Table(name = "IPsubnet")
public class IPsubnet {

	@ApiModelProperty( value = "Id",  dataType = "Integer")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty( value = "Related ir21 value",  dataType = "Ir21")
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ir21Id",referencedColumnName="id")
	private Ir21 ir21Id;
	
	@ApiModelProperty( value = "Raw ip value",  dataType = "String")
	private String rawIp;
	
	@ApiModelProperty( value = "subnet mask",  dataType = "String")
	private String subnetMask;

	@ApiModelProperty( value = "Ip value with mask",  dataType = "String")
	private String ipV4Address;
	
	@ApiModelProperty( value = "Date created",  dataType = "String")
	@CreatedDate
	private Timestamp createdDate;
}
