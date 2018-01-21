package com.bulletproof.onlineshop.core.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import com.bulletproof.onlineshop.api.Product;
import com.bulletproof.onlineshop.api.ProductType;

//field names are hard coded in this class to quickly complete the task but they can be put as constants in a different file as a best practice.
public class ProductMapper {

	// instead of hard coding field names we could use reflection to read field
	// names and values.
	public static SolrInputDocument mapToSolrDocument(Product p) {

		SolrInputDocument si = new SolrInputDocument();
		for (Entry<String, String> entry : p.getAttributes().entrySet()) {
			si.setField("attributes." + entry.getKey(), entry.getValue());
		}

		for (String value : p.getStoreIds()) {
			si.setField("storeIds", value);
		}

		si.setField("id", p.getId());
		si.setField("name", p.getName());
		si.setField("description", p.getDescription());
		si.setField("lastUpdatedTime", p.getLastUpdatedTime());
		si.setField("sku", p.getSku());
		si.setField("type", p.getType());
		si.setField("price", p.getPrice());
		si.setField("id", p.getId());

		return si;
	}

	public static List<Product> mapToProducts(SolrDocumentList sdl) {
		List<Product> productList = new ArrayList<>();
		sdl.forEach((SolrDocument sd) -> {
			Product p = new Product();
			p.setId(sd.getFieldValue("id").toString());
			p.setName(sd.getFieldValue("name") != null ? sd.getFieldValue("name").toString() : null);
			p.setDescription(sd.getFieldValue("descripton") != null ? sd.getFieldValue("description").toString() : null);
			p.setLastUpdatedTime(sd.getFieldValue("lastUpdatedTime") != null ? Long.valueOf(sd.getFieldValue("lastUpdatedTime").toString()) : null);
			p.setImagePath(sd.getFieldValue("imagePath") != null ? sd.getFieldValue("imagePath").toString() : null);
			p.setSku(sd.getFieldValue("sku") != null ? sd.getFieldValue("sku").toString() : null);
			p.setPrice(sd.getFieldValue("price") != null ? Double.valueOf(sd.getFieldValue("price").toString()) : null);
			p.setType(sd.getFieldValue("type") != null ? ProductType.valueOf(sd.getFieldValue("type").toString()) : null);
			
			///storeids
			List<String> storeIds = new ArrayList<>();
			if(sd.getFieldValues("storeIds") != null) {
				sd.getFieldValues("storeIds").forEach(o -> {
					storeIds.add(o.toString());
				});
				p.setStoreIds(storeIds);
			}
			
			//attributes
			Map<String,String> attributes = new HashMap<>();
			Map<String,Object> fvMap = sd.getFieldValueMap();
			for(String key : fvMap.keySet()) {
				if(key.startsWith("attributes.")) {
					attributes.put(key.replace("attributes.", ""), fvMap.get(key).toString());
				}
			}
			
			p.setAttributes(attributes);
			productList.add(p);
		});
		return productList;
	}
}
