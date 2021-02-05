package com.sample.xmlread.util;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sample.xmlread.constant.Constant;
import com.sample.xmlread.exception.FileParsingException;
import com.sample.xmlread.model.IPsubnet;
import com.sample.xmlread.model.Ir21;

public class XmlUtil {

	public static List<IPsubnet> readXml(String path) {
		List<IPsubnet> recordList = new ArrayList<>();
		try {

			File file = new File(path);
			String documentName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\") + 1);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList ipAddrRangeNodeList = doc.getElementsByTagName(Constant.IPADDRESSORRANGE);
			NodeList dnsItemNodeList = doc.getElementsByTagName(Constant.DNSITEM);
			String tadigCode = doc.getElementsByTagName(Constant.TADIGCODETAG).item(0) == null ? ""
					: doc.getElementsByTagName(Constant.TADIGCODETAG).item(0).getTextContent();
			String networkName = doc.getElementsByTagName(Constant.NETWORKNAMETAG).item(0) == null ? ""
					: doc.getElementsByTagName(Constant.NETWORKNAMETAG).item(0).getTextContent();
			for (int itr = 0; itr < ipAddrRangeNodeList.getLength(); itr++) {
				Node node = ipAddrRangeNodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String ipV4Value = eElement.getElementsByTagName(Constant.IPADDRESSRANGE).item(0).getTextContent();
					generateRecord(tadigCode, networkName, ipV4Value, documentName,recordList);
				}
			}
			for (int itr = 0; itr < dnsItemNodeList.getLength(); itr++) {
				Node node = dnsItemNodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String ipV4Value = eElement.getElementsByTagName(Constant.IPADDRESS).item(0).getTextContent();
					generateRecord(tadigCode, networkName, ipV4Value, documentName, recordList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new FileParsingException(e.getMessage());
		}
		return recordList;
	}

	private static void generateRecord(String tadigCode, String networkName, String ipV4Value, String documentName, List<IPsubnet> recordList) throws UnknownHostException {
		IPsubnet subnet = new IPsubnet();
		Ir21 ir21 = new Ir21();
		ir21.setNetworkName(networkName);
		ir21.setTadIgCode(tadigCode);
		ir21.setDocumentName(documentName);
		
		setIpAndMask(ipV4Value, subnet);
		subnet.setIr21Id(ir21);
		subnet.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		if(!checkDuplicate(recordList, subnet.getIpV4Address())) {
			recordList.add(subnet);
		}
		
	}

	private static boolean checkDuplicate(List<IPsubnet> recordList, String ipV4Value) {
		return recordList.stream()
				.map(IPsubnet::getIpV4Address)
				.filter(ipV4Value::equals).findFirst().isPresent();
	}

	private static void setIpAndMask(String ipV4Value, IPsubnet subnet) throws UnknownHostException {
		if (!ipV4Value.contains("/")) {
			ipV4Value = ipV4Value + Constant.DEFAULT_MASK;
		}

		String[] parts = ipV4Value.split("/");
		String ip = parts[0];
		int prefix;
		if (parts.length < 2) {
			prefix = 0;
		} else {
			prefix = Integer.parseInt(parts[1]);
		}
		int mask = 0xffffffff << (32 - prefix);
		int value = mask;
		byte[] bytes = new byte[] { (byte) (value >>> 24), (byte) (value >> 16 & 0xff), (byte) (value >> 8 & 0xff),
				(byte) (value & 0xff) };

		InetAddress netAddr = InetAddress.getByAddress(bytes);
		subnet.setRawIp(ip);
		subnet.setSubnetMask(netAddr.getHostAddress());
		subnet.setIpV4Address(ipV4Value);
	}

}
