package com.bulletproof.onlineshop.dataloadingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//This module can be run as a command line tool to inject data into Solr cluster.
//SolrCloud with distributed indexing combined with  spring threadpooltaskexecutor can be used
//for loading huge amounts of transaction data.
@SpringBootApplication()
public class Application  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
	}
}
