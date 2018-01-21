package com.bulletproof.onlineshop.core.dao.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bulletproof.onlineshop.api.Product;
import com.bulletproof.onlineshop.core.dao.PersistenceException;
import com.bulletproof.onlineshop.core.dao.ProductDao;
import com.bulletproof.onlineshop.core.mapping.ProductMapper;

@Repository
@Component
public class ProductDaoImpl implements ProductDao{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	@Autowired
	@Qualifier("products")
	private SolrClient solrClient;
	

	@Override
	public void create(Product product) {
		SolrInputDocument si = ProductMapper.mapToSolrDocument(product);
		try {
			solrClient.add(si);
			solrClient.commit();
		} catch (SolrServerException | IOException e) {
			logger.error("Error occurred while persisting product to the database.", e);
			throw new PersistenceException("Error occurred while persisting product with id "+ product.getId() +  "into the database.", e);
		}
	}

	@Override
	public Product get(String ID) {
		SolrQuery sq = new SolrQuery();
		sq.setQuery("id:" + ID);
		try {
			QueryResponse qr = solrClient.query(sq);
			List<Product> pList = ProductMapper.mapToProducts(qr.getResults());
			if(pList.size() > 0) {
				return pList.get(0);
			}
		} catch (SolrServerException | IOException e) {
			logger.error("Error occurred while retrieving products from the database.", e);
			throw new PersistenceException("Error occurred while retrieving products from the database.", e);
		}
		return null;
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> findByStore(String storeId, int start, int rows) {
		SolrQuery sq = new SolrQuery();
		sq.setQuery("storeIds:" + storeId);
		sq.setStart(start);
		sq.setRows(rows);
		try {
			QueryResponse qr = solrClient.query(sq);
			return ProductMapper.mapToProducts(qr.getResults());
		} catch (SolrServerException | IOException e) {
			logger.error("Error occurred while retrieving products from the database.", e);
			throw new PersistenceException("Error occurred while retrieving products from the database.", e);
		}
	}

}
