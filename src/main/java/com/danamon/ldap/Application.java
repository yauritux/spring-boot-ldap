package com.danamon.ldap;

import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author yauritux@gmail.com
 */
@SpringBootApplication
@PropertySource("classpath:ldap.properties")
public class Application {
	
	@Value("${host}")
	private String host;
	
	@Value("${port}")
	private int port;
	
	public static void main(String... args) {
  		SpringApplication.run(Application.class, args);
	}	
	
	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
	
	@Bean
	public LdapConnection ldapConnection() {
		final LdapConnection ldapConnection = new LdapNetworkConnection(host, port);
		return ldapConnection;
	}
}
