package com.springbook.iod.injection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionBean {

	// List
	private List<String> addressList;

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	
	// Set
	private Set<String> addressSet;

	public Set<String> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}
	
	// Map
	private Map<String,String> addressMap;

	public Map<String,String> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<String,String> addressMap) {
		this.addressMap = addressMap;
	}
	
	// Properties
	private Properties addressProperties;

	public Properties getAddressProperties() {
		return addressProperties;
	}

	public void setAddressProperties(Properties addressProperties) {
		this.addressProperties = addressProperties;
	}
	
}
