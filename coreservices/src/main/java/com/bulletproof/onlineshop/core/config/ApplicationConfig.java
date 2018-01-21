package com.bulletproof.onlineshop.core.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import com.bulletproof.onlineshop.core.dao.ProductDao;
import com.bulletproof.onlineshop.core.dao.impl.ProductDaoImpl;

@Configuration
public class ApplicationConfig {
	
	@Value("${solr.host}")
	private String solrHost;
	
	@Value("${solr.port}")
	private String solrPort;

	@Bean
	@Qualifier("products")
	public SolrClient solrClientProducts() {
		String urlString = "http://" + solrHost + ":" + solrPort + "/solr/products";
		SolrClient solrClient = new HttpSolrClient.Builder(urlString).build();
		return solrClient;
	}
	
	@Bean
	@Qualifier("stores")
	public SolrClient solrClientStores() {
		String urlString = "http://" + solrHost + ":" + solrPort + "/solr/stores";
		SolrClient solrClient = new HttpSolrClient.Builder(urlString).build();
		return solrClient;
	}
	
//	@Bean
//	public ProductDao productDao() {
//	 return new ProductDaoImpl();
//	}
	
	@Bean
	UriComponentsBuilder uri( ) {
		return UriComponentsBuilder.newInstance();
	}
	
}
