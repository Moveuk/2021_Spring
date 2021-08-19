package com.springbook.iod.injection;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {

	public static void main(String[] args) {
		// Factory
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		// List
		CollectionBean bean = (CollectionBean) factory.getBean("collectionBeanList");

		List<String> addressList = bean.getAddressList();

		for (String address : addressList) {
			System.out.println(address);
		}

		// Set
		bean = (CollectionBean) factory.getBean("collectionBeanSet");

		Set<String> addressSet = bean.getAddressSet();

		for (String address : addressSet) {
			System.out.println(address);
		}

		// Map
		bean = (CollectionBean) factory.getBean("collectionBeanMap");

		Map<String, String> addressMap = bean.getAddressMap();
		Set<String> keySet = null;

		if (addressMap != null) {
			keySet = addressMap.keySet();
		}

		if (keySet != null) {
			// KeySet 사용
			int num = 1;
			for (String key : keySet) {
				System.out.println(num + "번째 ");
				System.out.print("key : " + key);
				System.out.print("value : " + addressMap.get(key));
				System.out.println();
				num++;
			}

			for (String key : addressMap.keySet()) {
				System.out.println(String.format("키 : %s, 값 : %s", key, addressMap.get(key)));
			}

			// EntrySet 사용
			for (Entry<String, String> entrySet : addressMap.entrySet()) {
				System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
			}

		}
		
		// Properties
		bean = (CollectionBean) factory.getBean("collectionBeanProperties");
		
		Properties addressProperties = bean.getAddressProperties();
		
		for(String key : addressProperties.stringPropertyNames()) {
			System.out.println(String.format("키 : %s, 값 : %s", key, addressProperties.get(key)));
		}
	}
}
