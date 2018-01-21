package com.bulletproof.onlineshop.core.dao.impl;

import com.bulletproof.onlineshop.core.dao.PersistenceException;
import com.bulletproof.onlineshop.core.dao.StoreDao;
import com.bulletproof.onlineshop.core.mapping.StoreMapper;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.bulletproof.onlineshop.api.Store;

@Repository
public class StoreDaoImpl implements StoreDao{
	
	private static final Logger logger = LoggerFactory.getLogger(StoreDaoImpl.class);
	
	@Autowired
	@Qualifier("stores")
	private SolrClient solrClient;

	@Override
	public void create(Store store) {
		SolrInputDocument si = StoreMapper.mapToSolrDocument(store);
		try {
			solrClient.add(si);
			solrClient.commit();
		} catch (SolrServerException | IOException e) {
			logger.error("Error occurred while persisting product to the database.", e);
			throw new PersistenceException("Error occurred while persisting product with id "+ store.getId() +  "into the database.", e);
		}
		
	}

	@Override
	public Store get(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Store t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String ID) {
		// TODO Auto-generated method stub
		
	}

}
