package com.danamon.ldap.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yauritux@gmail.com
 *
 */
public class LdapRequestModel {

	private String baseDn;
	private String filter;
	private String scope;
	private String attributes;
	
	public LdapRequestModel() {}
	
	@JsonProperty("base_dn")
	public String getBaseDn() {
		return baseDn;
	}
	
	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}
	
	@JsonProperty("filter")
	public String getFilter() {
		return filter;
	}
	
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	@JsonProperty("search_scope")
	public String getScope() {
		return scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	@JsonProperty("attributes")
	public String getAttributes() {
		return attributes;
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
}