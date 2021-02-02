package com.sample.xmlread.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class IPsubnetdto {

	private String networkName;
	private String tadigCode;
	private String documentName;
	private List<String> rawip;
	private Timestamp lastIpDate;
}
