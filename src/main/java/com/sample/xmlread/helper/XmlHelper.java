package com.sample.xmlread.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sample.xmlread.dto.IPsubnetdto;
import com.sample.xmlread.model.IPsubnet;

@Component
public class XmlHelper {

	public List<IPsubnetdto> toDtoList(List<IPsubnet> ipList) {

		List<IPsubnetdto> subnetDtoList = new ArrayList<>();
		for(IPsubnet subnet : ipList) {
			if(CollectionUtils.isEmpty(subnetDtoList)) {
				setValues(subnetDtoList, subnet);
			} else {
				boolean flag=false;
				for (IPsubnetdto subnetdto : subnetDtoList) {
					if (subnetdto.getDocumentName().equals(subnet.getIr21Id().getDocumentName())) {
						List<String> ip = new ArrayList<String>(subnetdto.getRawip());
						ip.add(subnet.getIpV4Address());
						subnetdto.setRawip(ip);
						if (subnet.getCreatedDate().compareTo(subnetdto.getLastIpDate()) > 0) {
							subnetdto.setLastIpDate(subnet.getCreatedDate());
						}
						flag = true;
					}
				}
				if(!flag) {
					setValues(subnetDtoList, subnet);
				}
			}
		}
		return subnetDtoList;
		
	
	}

	private void setValues(List<IPsubnetdto> subnetDtoList, IPsubnet subnet) {
		IPsubnetdto dto = new IPsubnetdto();
		dto.setDocumentName(subnet.getIr21Id().getDocumentName());
		dto.setNetworkName(subnet.getIr21Id().getNetworkName());
		dto.setTadigCode(subnet.getIr21Id().getTadIgCode());
		dto.setLastIpDate(subnet.getCreatedDate());
		dto.setRawip(Arrays.asList(subnet.getIpV4Address()));
		subnetDtoList.add(dto);
	}

}
