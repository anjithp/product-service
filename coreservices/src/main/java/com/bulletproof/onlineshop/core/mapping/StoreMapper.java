package com.bulletproof.onlineshop.core.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.bulletproof.onlineshop.api.Store;
import com.bulletproof.onlineshop.api.Address;
import com.bulletproof.onlineshop.api.ProductType;

public class StoreMapper {

	
	// instead of hard coding field names we could use reflection to read field
		// names and values.
		public static SolrInputDocument mapToSolrDocument(Store s) {

			SolrInputDocument si = new SolrInputDocument();

			si.setField("id", s.getId());
			si.setField("name", s.getName());
			si.setField("description", s.getDescription());
			si.setField("lastUpdatedTime", s.getLastUpdatedTime());
			si.setField("longitude", s.getLongitude());
			si.setField("latitude", s.getLatitude());
			
			Address address = s.getAddress();
			if(address != null) {
				si.setField("address.line1", address.getLine1());
				si.setField("address.city", address.getCity());
				si.setField("address.country", address.getCountry());
				si.setField("address.state", address.getState());
				si.setField("address.zipcode", address.getZipcode());
			}
			
			
			return si;
		}

		
}
