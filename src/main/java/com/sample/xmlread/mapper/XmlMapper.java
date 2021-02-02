/*
 * package com.sample.xmlread.mapper;
 * 
 * import java.util.ArrayList; import java.util.Arrays; import java.util.List;
 * 
 * import org.mapstruct.Mapper; import org.springframework.util.CollectionUtils;
 * 
 * import com.sample.xmlread.dto.IPsubnetdto; import
 * com.sample.xmlread.model.IPsubnet;
 * 
 * @Mapper(componentModel = "spring") public interface XmlMapper {
 * 
 * IPsubnetdto toDto(IPsubnet ipList);
 * 
 * default List<IPsubnetdto> toDtoList(List<IPsubnet> ipList){ List<IPsubnetdto>
 * subnetDtoList = new ArrayList<>(); for(IPsubnet subnet : ipList) {
 * if(CollectionUtils.isEmpty(subnetDtoList)) { IPsubnetdto dto = new
 * IPsubnetdto(); dto.setDocumentName(subnet.getIr21Id().getDocumentName());
 * dto.setNetworkName(subnet.getIr21Id().getNetworkName());
 * dto.setTadigCode(subnet.getIr21Id().getTadIgCode());
 * dto.setLastIpDate(subnet.getCreatedDate());
 * dto.setRawip(Arrays.asList(subnet.getIpV4Address())); subnetDtoList.add(dto);
 * } else { boolean flag=false; for (IPsubnetdto subnetdto : subnetDtoList) { if
 * (subnetdto.getDocumentName().equals(subnet.getIr21Id().getDocumentName())) {
 * List<String> ip = subnetdto.getRawip(); ip.add(subnet.getIpV4Address());
 * subnetdto.setRawip(ip); if
 * (subnet.getCreatedDate().compareTo(subnetdto.getLastIpDate()) > 0) {
 * subnetdto.setLastIpDate(subnet.getCreatedDate()); } flag = true; } }
 * if(!flag) { IPsubnetdto dto = new IPsubnetdto();
 * dto.setDocumentName(subnet.getIr21Id().getDocumentName());
 * dto.setNetworkName(subnet.getIr21Id().getNetworkName());
 * dto.setTadigCode(subnet.getIr21Id().getTadIgCode());
 * dto.setLastIpDate(subnet.getCreatedDate());
 * dto.setRawip(Arrays.asList(subnet.getIpV4Address())); subnetDtoList.add(dto);
 * } } } return subnetDtoList;
 * 
 * } }
 */