package com.ericsson.v1.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.domain.KPIRoleAssignment;
import com.ericsson.ipm.v1.domain.Role;
import com.ericsson.ipm.v1.dto.KPIDTO;
import com.ericsson.ipm.v1.dto.KPIRoleDTO;
import com.ericsson.ipm.v1.service.RoleService;

public class IPMUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IPMUtility.class);
	
	private static final Map<String, String> map = new HashMap<String, String>();
	private static final Configuration CONFIG = AdaptersConfiguration
			.getInstance().getConfiguration();
	
	static {
		Object IPM_HEADER_MENU = CONFIG.getProperty("ipm.header.menu");
		LOGGER.info("IPM_HEADER_MENU : "+IPM_HEADER_MENU);
		
		
		map.put("Core Values" , "ipmDashboard.html") ;  
		map.put("Service Satisfaction (As per RcSE)" , "ipmDashboard.html") ;  
		map.put("Service Satisfaction" , "ipmDashboard.html") ;  
		map.put("Operational Discipline" , "ipmDashboard.html") ;  
		map.put("Utilization" , "ipmDashboard.html") ;  
		map.put("3PP Certification" , "ipmDashboard.html") ;  
		map.put("Delivery quality" , "deliveryQualityDetails.html") ;  
		map.put("Engage in Multiple Projects And Others" , "assetDetails.html");

		
		
		/*if(IPM_HEADER_MENU instanceof List){
			List<String> list = (List<String>())IPM_HEADER_MENU;
		}*/
			/*for(String val : (List)IPM_HEADER_MENU){
		}*/
		//String[] valuse = IPM_HEADER_MENU.split(",");
		/*for(int i =0; i <valuse.length; i++){
			String[] temps = valuse[i].split(":");
			for(int j =0; j <temps.length; j++){
				map.put(temps[0], temps[1]);
			}
		}*/
		
	}

	public static Map<String, String> spiltMeta(String key, String metaVal,
			int metaValMaxLengthInt) {
		Map<String, String> oneKlenStrMap = new LinkedHashMap<String, String>();
		if (metaVal == null) {
			return oneKlenStrMap;
		}

		if (metaVal.length() <= metaValMaxLengthInt) {
			oneKlenStrMap.put(key, metaVal);
			return oneKlenStrMap;
		}

		for (int i = 0, j = 0; i < metaVal.length(); ++j) {
			String oneKLen = null;
			String newKey = key;
			if (j != 0) {
				newKey = key + ":" + j;
			}
			if (i + metaValMaxLengthInt < metaVal.length()) {
				oneKLen = metaVal.substring(i, i + metaValMaxLengthInt);
				int lastCommaIdx = oneKLen.lastIndexOf(",");
				String remStr = metaVal.substring(i);
				if (lastCommaIdx < 0 && remStr != null && remStr.length() <= metaValMaxLengthInt) {
					oneKlenStrMap.put(newKey, remStr);
					return oneKlenStrMap;
				}
				if (lastCommaIdx < 0) {
					lastCommaIdx = metaValMaxLengthInt - 1;
				}
				if (lastCommaIdx <= oneKLen.length()) {
					oneKlenStrMap.put(newKey,
							oneKLen.substring(0, lastCommaIdx + 1));
				}
				i = lastCommaIdx + 1 + i;
			} else {
				oneKlenStrMap.put(newKey, metaVal.substring(i));
				i = metaVal.length() + 1;
			}
		}
		return oneKlenStrMap;
	}
	
	public static Map<String, String> spiltText(String key, String metaVal,
			int metaValMaxLengthInt) {
		String keyLastIndex = ""+key.charAt(key.length()-1);
		String metaKey = key.substring(0, key.length()-1);
		Integer k = Integer.parseInt(keyLastIndex);
		Map<String, String> oneKlenStrMap = new LinkedHashMap<String, String>();
		if (metaVal == null) {
			return oneKlenStrMap;
		}

		if (metaVal.length() <= metaValMaxLengthInt) {
			oneKlenStrMap.put(key, metaVal);
			return oneKlenStrMap;
		}

		for (int i = 0, j = 0; i < metaVal.length(); ++j) {
			String oneKLen = null;
			String newKey = key;
			if (j != 0) {
				newKey = metaKey + (j+1);
			}
			if (i + metaValMaxLengthInt < metaVal.length()) {
				oneKLen = metaVal.substring(i, i + metaValMaxLengthInt);
				int lastCommaIdx = oneKLen.lastIndexOf(",");
				String remStr = metaVal.substring(i);
				if (lastCommaIdx < 0 && remStr != null && remStr.length() <= metaValMaxLengthInt) {
					oneKlenStrMap.put(newKey, remStr);
					return oneKlenStrMap;
				}
				if (lastCommaIdx < 0) {
					lastCommaIdx = metaValMaxLengthInt - 1;
				}
				if (lastCommaIdx <= oneKLen.length()) {
					oneKlenStrMap.put(newKey,
							oneKLen.substring(0, lastCommaIdx + 1));
				}
				i = lastCommaIdx + 1 + i;
			} else {
				oneKlenStrMap.put(newKey, metaVal.substring(i));
				i = metaVal.length() + 1;
			}
			if(j >= 3)
				break;
		}
		
		return oneKlenStrMap;
	}
	
	
	/* public static void setKPIValue(String roleSC, String kpiValue, KPIRoleDTO dto) {
		
		 if(StringUtils.isBlank(kpiValue)){
			 kpiValue = "N/A";
		 }
			 switch (roleSC) {
			  case "SWD":
				  dto.setKpiValueSWD(kpiValue);
			       break;
			  case "SSWD":
				  dto.setKpiValueSSWD(kpiValue);
			       break;
			  case "SA":
				  dto.setKpiValueSA(kpiValue);
			       break;
			  case "SSA":
				  dto.setKpiValueSSA(kpiValue);
			       break;
			  case "SPM":
				  dto.setKpiValueSPM(kpiValue);
			       break;
			  case "CA":
				  dto.setKpiValueCA(kpiValue);
			       break;
			  case "RPM":
				  dto.setKpiValueRPM(kpiValue);
			       break;
			  case "SDL":
				  dto.setKpiValueSDL(kpiValue);
			       break;
			  case "DL":
				  dto.setKpiValueDL(kpiValue);
			       break;
			 default:
			       throw new IllegalArgumentException();
			 }
	 }*/
	
	
	 public static void setKPIValue(String roleSC, String kpiValue, KPIRoleDTO dto) {
			
		 if(StringUtils.isBlank(kpiValue)){
			 kpiValue = "N/A";
		 }
			if("SWD".equalsIgnoreCase(roleSC)){
				 dto.setKpiValueSWD(kpiValue);
			} else if ("SSWD".equalsIgnoreCase(roleSC)){
				  dto.setKpiValueSSWD(kpiValue);
			} else if ("SA".equalsIgnoreCase(roleSC)){
				 dto.setKpiValueSA(kpiValue);
			} else if ("SSA".equalsIgnoreCase(roleSC)){
				 dto.setKpiValueSSA(kpiValue);
			} else if ("SPM".equalsIgnoreCase(roleSC)){
				dto.setKpiValueSPM(kpiValue);
			} else if ("CA".equalsIgnoreCase(roleSC)){
				 dto.setKpiValueCA(kpiValue);
			} else if ("RPM".equalsIgnoreCase(roleSC)){
				 dto.setKpiValueRPM(kpiValue);
			} else if ("SDL".equalsIgnoreCase(roleSC)){
				 dto.setKpiValueSDL(kpiValue);
			} else if ("DL".equalsIgnoreCase(roleSC)){
				 dto.setKpiValueDL(kpiValue);
			}
	 }
	 
	 
	 public static List<KPIRoleAssignment> populateKPIRoleFromUI(KPIDTO kpiDTO, RoleService roleService, HttpServletRequest request){
		 
		    List<KPI> kpis = new ArrayList<KPI>();
			List<KPIRoleAssignment> kpiRoleAssignments = new ArrayList<KPIRoleAssignment>();
			
			
			if(kpiDTO.getUserSelectedRoles() != null){
					for(Integer roleId : kpiDTO.getUserSelectedRoles()){
						Role role = roleService.findById(roleId);
						KPIRoleAssignment kpiRoleAssignment = new KPIRoleAssignment();
						KPI kpi = new KPI();
						kpi.setKpiDisplayName(kpiDTO.getKpiDisplayName());
						
						// spliting name 
						Map<String, String> mapA1 = spiltText("kpiName1",
								kpiDTO.getKpiName(), 1000);
						
						kpi.setKpiName1(mapA1.get("kpiName1"));
						if (StringUtils.isNotBlank(mapA1.get("kpiName2")))
							kpi.setKpiName1(mapA1.get("kpiName2"));
						if (StringUtils.isNotBlank(mapA1.get("kpiName3")))
							kpi.setKpiName1(mapA1.get("kpiName3"));
						if (StringUtils.isNotBlank(mapA1.get("kpiName4")))
							kpi.setKpiName1(mapA1.get("kpiName4"));
						
						
						// spliting description/comment
						
						String kpiCommentValue = null;
						
						if(kpiDTO.getUserSelectedRoles().size() > 1){
							kpiCommentValue = getParamValue("comment",role,request);
							kpi.setKpiValue(getParamValue("kpiValue",role,request));
						} else {
							kpi.setKpiValue(kpiDTO.getKpiValue());
							kpiCommentValue = kpiDTO.getKpiDescription();
						}
						
						Map<String, String> mapB1 = spiltText("kpiDescription1",
								kpiCommentValue, 1000);
						
						kpi.setKpiDescription1(mapB1.get("kpiDescription1"));
						if (StringUtils.isNotBlank(mapB1.get("kpiDescription2")))
							kpi.setKpiDescription2(mapB1.get("kpiDescription2"));
						if (StringUtils.isNotBlank(mapB1.get("kpiDescription3")))
							kpi.setKpiDescription3(mapB1.get("kpiDescription3"));
						if (StringUtils.isNotBlank(mapB1.get("kpiDescription4")))
							kpi.setKpiDescription4(mapB1.get("kpiDescription4"));
						
						
						
						kpis.add(kpi);
						
						
						kpiRoleAssignment.setKpi(kpi);
						kpiRoleAssignment.setRole(role);
						kpiRoleAssignments.add(kpiRoleAssignment);
						
					}
					
					/*for(KPI kpi : kpis){
						LOGGER.info("kpi : "+kpi);
					}
					
					for(KPIRoleAssignment kpiRoleAssignment : kpiRoleAssignments){
						LOGGER.info("kpiRoleAssignment : "+kpiRoleAssignment);
						LOGGER.info("kpiRoleAssignment getKpi : "+kpiRoleAssignment.getKpi().getId());
						LOGGER.info("kpiRoleAssignment getRole : "+kpiRoleAssignment.getRole().getId());
					}*/
				}
		 
		 return kpiRoleAssignments;
	 }
	 
	 private static String getParamValue(String paramPrefix, Role role, HttpServletRequest request){
			String kpiParam = request.getParameter(paramPrefix+role.getCode());
			return kpiParam;
		}
	 
	 public static String getParamValue(String KpiDisplayName){
		 return map.get(KpiDisplayName);
	 }
	
}
