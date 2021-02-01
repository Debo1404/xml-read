package com.sample.xmlread.util;

import java.io.File;
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
import com.sample.xmlread.model.IPsubnet;
import com.sample.xmlread.model.Ir21;

public class XmlUtil {

	public static List<IPsubnet> readXml(String path) {
		List<IPsubnet> recordList = new ArrayList<>();
		try {

			File file = new File(path);
			String documentName = path.substring(path.lastIndexOf("\\")).replace("\\", "");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList ipAddrRangeNodeList = doc.getElementsByTagName("IPAddressOrRange");
			NodeList dnsItemNodeList = doc.getElementsByTagName("DNSitem");
			String tadigCode = doc.getElementsByTagName(Constant.TADIGCODETAG).item(0).getTextContent();
			String networkName = doc.getElementsByTagName(Constant.NETWORKNAMETAG).item(0).getTextContent();
			System.out.println("ipAddrRangeNodeList lentgh: " + ipAddrRangeNodeList.getLength());
			for (int itr = 0; itr < ipAddrRangeNodeList.getLength(); itr++) {
				Node node = ipAddrRangeNodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String ipV4Value = eElement.getElementsByTagName("IPAddressRange").item(0).getTextContent();
					IPsubnet subnet = generateRecord(tadigCode,networkName, ipV4Value, documentName);
					recordList.add(subnet);
				}
			}
			System.out.println("dnsItemNodeList lentgh: " + dnsItemNodeList.getLength());
			for (int itr = 0; itr < dnsItemNodeList.getLength(); itr++) {
				Node node = dnsItemNodeList.item(itr);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String ipV4Value = eElement.getElementsByTagName("IPAddress").item(0).getTextContent();
					IPsubnet subnet = generateRecord(tadigCode,networkName, ipV4Value, documentName);
					recordList.add(subnet);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}

	private static IPsubnet generateRecord(String tadigCode, String networkName, String ipV4Value, String documentName) {
		IPsubnet subnet = new IPsubnet();
		Ir21 ir21 = new Ir21();
		ir21.setNetworkName(networkName);
		ir21.setTadIgCode(tadigCode);
		ir21.setDocumentName(documentName);
		
		subnet.setIpV4Address(ipV4Value);
		subnet.setRawIp(ipV4Value);
		subnet.setSubnetMask(ipV4Value);
		subnet.setIr21Id(ir21);
		subnet.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		return subnet;
		
	}

}
