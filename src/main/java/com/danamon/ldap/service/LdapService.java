package com.danamon.ldap.service;

import java.io.IOException;
import java.util.List;

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;

/**
 * 
 * @author yauritux@gmail.com
 *
 */
public interface LdapService {
	
	public void connect(String server, int port) throws Exception;
	public void bind() throws LdapException, IOException;
	public void bind(String user, String password) throws LdapException, IOException;
	public void unbind() throws LdapException;
	public List<String> fetchData(String baseDn, String filter, SearchScope scope,
			String attributes) throws Exception;
}