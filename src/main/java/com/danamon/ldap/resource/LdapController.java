package com.danamon.ldap.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.directory.api.ldap.model.message.SearchScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.danamon.ldap.dto.request.LdapRequestModel;
import com.danamon.ldap.dto.response.ApiResponseWrapper;
import com.danamon.ldap.service.LdapService;

/**
 * 
 * @author yauritux@gmail.com
 *
 */
@RestController
@RequestMapping("/api/ldap")
@PropertySource("classpath:ldap.properties")
public class LdapController {

	private LdapService ldapService;
	
	@Value("${host}")
	private String host;
	
	@Value("${port}")
	private int port;
	
	@Value("${dn}")
	private String dn;
	
	@Value("${credentials}")
	private String credentials;
	
	@Autowired
	public void setLdapService(LdapService ldapService) {
		this.ldapService = ldapService;
	}
	
	@RequestMapping(value = "/connect", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseWrapper<Map<String, String>> connect() throws Exception {
		ApiResponseWrapper<Map<String, String>> response = new ApiResponseWrapper<>();;
		
		try {
			ldapService.connect(host, port);
			if (dn != null && !dn.isEmpty() && credentials != null && !credentials.isEmpty()) {
				System.out.println("user=" + dn);
				System.out.println("credentials=" + credentials);
				ldapService.bind(dn, credentials);
			} else {
				ldapService.bind();
			}
			Map<String, String> data = new HashMap<>();
			data.put("operation", "Open LDAP Connection");
			data.put("response", "OK");
			response.setData(data);
			response.setMessage("Successfully connected to LDAP Server");
		} catch (Exception e) {
			throw e;
		} finally {
			ldapService.unbind();
		}
		
		return response;
	}
	
	@RequestMapping(value = "/fetch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseWrapper<Map<String, String>> fetch(@RequestBody LdapRequestModel request) throws Exception {
		ApiResponseWrapper<Map<String, String>> response = new ApiResponseWrapper<>();
		
		StringBuilder strBuilder = new StringBuilder();
		try {
			ldapService.connect(host, port);
			if (dn != null && !dn.isEmpty() && credentials != null && !credentials.isEmpty()) {
				ldapService.bind(dn, credentials);
			} else {
				ldapService.bind();
			}
			Map<String, String> data = new HashMap<>();
			SearchScope scope = SearchScope.valueOf(request.getScope());
			List<String> list = ldapService.fetchData(request.getBaseDn(), request.getFilter(),
					scope, request.getAttributes());
			for (String str : list) {
				strBuilder.append(str).append(",");
			}
			data.put("operation", "Fetching LDAP Data");
			data.put("response", strBuilder.toString());
			response.setData(data);
			response.setMessage("success");
		} catch (Exception e) {
			throw e;
		} finally {
			ldapService.unbind();
		}
		
		return response;
	}
}