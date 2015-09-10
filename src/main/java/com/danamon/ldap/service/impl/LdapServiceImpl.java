package com.danamon.ldap.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danamon.ldap.service.LdapService;

/**
 * 
 * @author yauritux@gmail.com
 *
 */
@Service("ldapService")
public class LdapServiceImpl implements LdapService {
	
	private LdapConnection ldapConnection;
	
	@Autowired
	LdapServiceImpl(LdapConnection ldapConnection) {
		this.ldapConnection = ldapConnection;
	}
	
	@Override
	public void bind() throws LdapException, IOException {
		if (ldapConnection != null) {
			ldapConnection.bind();
		} else {
			throw new LdapException("No Active Connection. Please connect first!");
		}
	}
	
	public void bind(String user, String password) throws LdapException, IOException {
		if (ldapConnection != null) {
			ldapConnection.bind(user, password);
		} else {
			throw new LdapException("No Active Connection. Please connect first!");
		}
	}
	
	@Override
	public void unbind() throws LdapException {
		if (ldapConnection != null) {
			ldapConnection.unBind();
		} else {
			throw new LdapException("No Active connection. Please connect and bind first!");
		}
	}

	@Override
	public List<String> fetchData(String baseDn, String filter, SearchScope scope,
			String attributes) throws Exception {
		List<String> list = new ArrayList<>();
		if (ldapConnection != null) {
			/*
			EntryCursor cursor = ldapConnection.search("ou=people", "(objectclass=*)",
					SearchScope.ONELEVEL, "*");
			*/
			EntryCursor cursor = ldapConnection.search(baseDn, filter, scope, attributes);
			
			while (cursor.next()) {
				Entry entry = cursor.get();
				list.add(entry.getDn().getName());
			}
		}
		
		return list;
	}
}