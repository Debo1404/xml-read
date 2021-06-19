package com.sample.xmlread.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel (
	    value       = "IR21 values",
	    description = "This table hold value related to document."
	)
@Entity
@Data
@Table(name = "Ir21")
public class Ir21 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty( value = "name of document",  dataType = "String")
	private String documentName;

	@ApiModelProperty( value = "tadig code",  dataType = "String")
	private String tadIgCode;

	@ApiModelProperty( value = "network name",  dataType = "String")
	private String networkName;
}
